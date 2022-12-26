package com.workflow.workflow.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class CusUsernamePasswordAuthenticationFilter extends
    AbstractAuthenticationProcessingFilter {

  CusUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(new AntPathRequestMatcher("/login", "POST"));
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    if (request.getContentType() == null) {
      throw new AuthenticationServiceException("请求头类型不支持: " +
          request.getContentType());
    }

    UsernamePasswordAuthenticationToken authRequest;
    System.out.println("测试测试");
    try {
      String username = obtainUsername(request);
      authRequest = new UsernamePasswordAuthenticationToken("admin", "admin",
          null);
    } catch (Exception e) {
      throw new AuthenticationServiceException(e.getMessage());
    }
    return this.getAuthenticationManager().authenticate(authRequest);
  }

  protected String obtainUsername(HttpServletRequest request) {
    return request.getParameter("username");
  }
}
