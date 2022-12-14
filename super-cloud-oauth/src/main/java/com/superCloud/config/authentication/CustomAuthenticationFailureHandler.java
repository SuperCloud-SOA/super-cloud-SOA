package com.superCloud.config.authentication;

import com.superCloud.common.utils.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author ys
 * @date 2022/8/3 13:30
 */
@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


  @Override
  public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    //1、认证失败后响应json字符串
    R result = R.build(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    httpServletResponse.setContentType("application/json;charset=UTF-8");
    httpServletResponse.getOutputStream().write(result.toJsonString().getBytes("UTF-8"));

  }
}
