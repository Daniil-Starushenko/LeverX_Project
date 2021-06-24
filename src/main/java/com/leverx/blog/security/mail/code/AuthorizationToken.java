package com.leverx.blog.security.mail.code;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("AuthorizationToken")
public class AuthorizationToken implements Serializable {

}
