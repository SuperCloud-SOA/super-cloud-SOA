package com.superCloud.config.authentication;

import com.superCloud.common.utils.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 * 1、决定响应json还是跳转页面 或者是认证成功后的其他处理
 * @author ys
 * @date 2022/8/3 13:30
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    R result = R.ok("认证成功");
    httpServletResponse.setContentType("application/json;charset=UTF-8");
    httpServletResponse.getOutputStream().write(result.toJsonString().getBytes("UTF-8"));

  }
}
