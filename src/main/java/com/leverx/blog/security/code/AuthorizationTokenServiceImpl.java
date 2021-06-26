package com.leverx.blog.security.code;

import com.leverx.blog.repository.redis.AuthorizationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorizationTokenServiceImpl implements AuthorizationTokenService{

    @Autowired
    private AuthorizationTokenRepository authorizationTokenRepository;

    @Override
    public AuthorizationToken saveAuthorizationToken(AuthorizationToken token) {
        log.info("Register token with id: {}", token.getTokenId());
        return authorizationTokenRepository.save(token);
    }



    @Override
    public boolean isDeleted(String tokenId) {
        log.info("check is active token with id: {}", tokenId);
        return authorizationTokenRepository.findTokenByTokenId(tokenId).isEmpty();
    }

    @Override
    public AuthorizationToken getTokenById(String tokenId) {
        log.info("get authorization token with id: {}", tokenId);
        return authorizationTokenRepository.findById(tokenId).get();
    }
}
