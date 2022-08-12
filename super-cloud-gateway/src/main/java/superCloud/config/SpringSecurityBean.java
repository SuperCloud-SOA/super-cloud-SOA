package superCloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * 统一管理 bena
 * @author ys
 * @date 2020/4/22 13:36
 */
@Configuration
public class SpringSecurityBean {

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



}
