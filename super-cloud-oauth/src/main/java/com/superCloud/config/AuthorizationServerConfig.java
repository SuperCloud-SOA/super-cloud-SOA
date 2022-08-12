package com.superCloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 认证配置类
 * @author ys
 * @date 2022/8/3 13:30
 */
@Configuration
@EnableAuthorizationServer //开启 OAuth2 认证服务器功能
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


  @Autowired
  public PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserDetailsService customUserDetailsService;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private ClientDetailsService jdbcClientDetailsService;
  //jwt转换器
  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;


  /**
   * 配置允许访问此认证服务器的客户端信息
   * @param clients
   * @throws Exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //JDBC管理客户端
    clients.withClientDetails(jdbcClientDetailsService);
  }


  /**
   * 关于认证服务器端点配置
   * @param endpoints
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    //密码模式需要这个实例
    endpoints.authenticationManager(authenticationManager);
    //刷新令牌需要使用该实例
    endpoints.userDetailsService(customUserDetailsService);
    //通过Redis管理令牌
    endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter);
  }


  /**
   * 令牌安全端点配置
   * @param security
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    //所有人可访问 /oauth/token_key 后面要获取公钥, 默认拒绝访问
    security.tokenKeyAccess("permitAll()");
    //认证后可访问 /oauth/check_token , 默认拒绝访问
    security.checkTokenAccess("isAuthenticated()");
  }
}
