package com.superCloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 使用Redis管理令牌
 * @author ys
 * @date 2022/8/3 13:30
 */
@Configuration
public class TokenConfig {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;


  @Bean
  public TokenStore tokenStore(){
    //通过Redis管理令牌
    return new RedisTokenStore(redisConnectionFactory);
  }


}
