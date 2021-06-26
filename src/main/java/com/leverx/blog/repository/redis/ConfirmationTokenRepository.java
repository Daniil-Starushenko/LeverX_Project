package com.leverx.blog.repository.redis;

import com.leverx.blog.security.code.ConfirmationToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ConfirmationTokenRepository extends KeyValueRepository<ConfirmationToken, String> {

   Optional<ConfirmationToken> findTokenByTokenId(String tokenId);
}
