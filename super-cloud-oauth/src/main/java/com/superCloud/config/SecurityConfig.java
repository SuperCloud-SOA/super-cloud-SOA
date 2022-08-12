package com.superCloud.config;

import com.superCloud.config.authentication.CustomAuthenticationFailureHandler;
import com.superCloud.config.authentication.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 安全配置类
 * @author ys
 * @date 2022/8/3 13:30
 */
@EnableWebSecurity //它包含了 @Configuration 注解所以不用加
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService customUserDetailsService;
  @Autowired
  private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123123")).authorities("product");
    //认证
    auth.userDetailsService(customUserDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().authorizeRequests()
            .anyRequest().permitAll();
  }

  @Bean // 密码模式需要此bean
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * 配置跨源访问(CORS)
   * @return
   */
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
}
