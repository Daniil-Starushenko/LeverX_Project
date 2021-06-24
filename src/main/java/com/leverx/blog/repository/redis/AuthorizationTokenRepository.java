package com.leverx.blog.repository.redis;

import com.leverx.blog.security.mail.code.AuthorizationToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorizationTokenRepository extends KeyValueRepository<AuthorizationToken, String> {

   Optional<AuthorizationToken> findTokenByTokenId(String tokenId);
}
