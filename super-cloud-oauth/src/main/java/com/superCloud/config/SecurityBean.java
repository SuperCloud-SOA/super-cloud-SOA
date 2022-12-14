package com.superCloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * 统一管理 bean
 * @author ys
 * @date 2022/8/3 13:30
 */
@Configuration
public class SecurityBean {


  public static final String SIGNING_KEY="cloud-oauth2-key";

  /**
   * 密码加密
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  //数据库数据源
  @ConfigurationProperties(prefix = "spring.datasource")
  @Bean
  public DataSource dataSource(){
    return new DruidDataSource();
  }

  //JDBC管理用户信息
  @Bean
  public ClientDetailsService jdbcClientDetailsService(){
    return new JdbcClientDetailsService(dataSource());
  }

  /**
   * JWT加密
   * @return
   */
  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter(){
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    //采用非对称加密方式 私钥加密
    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"),"oauth2".toCharArray());
    jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("oauth2"));
    return jwtAccessTokenConverter;
  }

}
