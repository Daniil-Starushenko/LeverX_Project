package com.leverx.blog.configuration;

import com.leverx.blog.repository.redis.RedisPackageMarker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@PropertySource("classpath:application.properties")
@EnableRedisRepositories(basePackageClasses = RedisPackageMarker.class)
public class RedisConfig {

    @Value("${redis.hostname}")
    private String redisHostname;

    @Value("${redis.port}")
    private Integer redisPort;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisHostname, redisPort);
        return new JedisConnectionFactory(redisConfig);
        //method with configuration of JedisConnectionFactory is deprecated
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
