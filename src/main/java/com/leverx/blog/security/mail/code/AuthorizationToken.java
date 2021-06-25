package com.leverx.blog.security.mail.code;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

//TODO lombok annotations

@Data
@RedisHash("AuthorizationToken")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class AuthorizationToken implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Indexed
    private String tokenId;

    private Integer userId;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long timeToLive;

}
