package superCloud.config;

import com.alibaba.nacos.common.utils.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 当前类用于管理所有的资源（认证服务器，资源服务器）
 * @author ys
 * @date 2020/4/23 16:36
 */
@Configuration
public class ResourceServerConfig {


  public static final String RESOURCE_ID = "system-server";

  @Autowired
  private TokenStore tokenStore;


  /**
   * 认证服务器资源
   */
//  @EnableResourceServer
  @EnableWebFluxSecurity
  public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      resources
        .resourceId(RESOURCE_ID) //资源服务器ID，认证服务端判断是否有该资源服务id的权限
        .tokenStore(tokenStore) //校验token合法性
      ;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.cors().and().authorizeRequests()
        .anyRequest().permitAll();//认证服务器 所有请求全部放行
    }

  }


  /**
   *系统服务器资源
   */
//  @EnableResourceServer
  @EnableWebFluxSecurity
  public class SystemResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      resources
        .resourceId(RESOURCE_ID) //资源服务器ID，认证服务端判断是否有该资源服务id的权限
        .tokenStore(tokenStore) //校验token合法性
      ;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
      http
        //跨域配置开始GlobalCorsConfig
        .cors().disable()
        .cors()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS)
        .permitAll().and()
        .authorizeRequests()
              .antMatchers("/system/weChat/**").permitAll()
        .antMatchers("/system/**").access("#oauth2.hasScope('SYSTEM_API')")
      ;
    }


//    /**
//     * 配置方式要换成 WebFlux的方式
//     */
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//      httpSecurity
//              .authorizeExchange().pathMatchers(HttpMethod.OPTIONS).permitAll()
//              // 任何请求需要身份认证
//              .pathMatchers("/**").permitAll().and()
//              .csrf().disable();
//      return httpSecurity.build();
//    }

  }

}
