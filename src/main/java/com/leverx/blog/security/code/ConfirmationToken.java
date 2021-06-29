package com.leverx.blog.security.code;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


@Data
@RedisHash("AuthorizationToken")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class ConfirmationToken implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Indexed
    private String tokenId;

    private Integer userId;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long timeToLive;

}
