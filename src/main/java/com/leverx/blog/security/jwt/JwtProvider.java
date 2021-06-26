package com.leverx.blog.security.jwt;

import com.leverx.blog.exception.auth.JwtAuthenticationException;
import com.leverx.blog.model.entity.UserStatus;
import com.leverx.blog.security.UserDetailServiceImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@PropertySource("classpath:application.properties")
public class JwtProvider {

    public static final String ROLE_CLAIM = "Role";
    public static final String AUTH_HEADER = HttpHeaders.AUTHORIZATION;
    public static final String TOKEN_PREAMBLE = "Bearer ";

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.validity.time.milliseconds}")
    private long validityTime;

    @Autowired
    private UserDetailsService userDetailsService;


    public String createToken(String email, UserStatus role) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put(ROLE_CLAIM, role.getAuthority());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTH_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREAMBLE)) {
            return bearerToken.substring(7);
        }
        return null;
    }


    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }



}
