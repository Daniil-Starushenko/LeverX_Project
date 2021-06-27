package com.leverx.blog.security.code;

import com.leverx.blog.exception.entity.EntityNotFoundException;
import com.leverx.blog.repository.redis.ConfirmationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository authorizationTokenRepository;

    @Override
    public ConfirmationToken saveConfirmationToken(ConfirmationToken token) {
        log.info("Register token with id: {}", token.getTokenId());
        return authorizationTokenRepository.save(token);
    }



    @Override
    public boolean isDeleted(String tokenId) {
        log.info("check is active token with id: {}", tokenId);
        return authorizationTokenRepository.findTokenByTokenId(tokenId).isEmpty();
    }

    @Override
    public boolean isActive(String tokenId) {
        log.info("check is active token with id: {}", tokenId);
        return authorizationTokenRepository.findTokenByTokenId(tokenId).isPresent();
    }

    @Override
    public ConfirmationToken getTokenById(String tokenId) {
        log.info("get authorization token with id: {}", tokenId);
        return authorizationTokenRepository.findById(tokenId)
                .orElseThrow(() -> new EntityNotFoundException("there is no such code: " + tokenId));
    }

    @Override
    public void deleteTokenByTokenId(String tokenId) {
        authorizationTokenRepository.deleteByTokenId(tokenId);
    }

    @Override
    public void deleteToken(ConfirmationToken tokenToDelete) {
        authorizationTokenRepository.delete(tokenToDelete);
    }

}
