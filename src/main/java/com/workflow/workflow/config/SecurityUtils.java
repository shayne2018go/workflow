package com.workflow.workflow.config;
/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

  public void logInAs(String username) {
    List<String> authoritiesStr = new ArrayList<String>();
    authoritiesStr.add("ROLE_ACTIVITI_USER");
    UserDetails user = new User(username, username,
        authoritiesStr.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList()));

    // if (user == null) {
    // throw new IllegalStateException("User " + username + " doesn't exist, please
    // provide a valid user");
    // }
    SecurityContextHolder.setContext(new SecurityContextImpl(new Authentication() {
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
      }

      @Override
      public Object getCredentials() {
        return user.getPassword();
      }

      @Override
      public Object getDetails() {
        return user;
      }

      @Override
      public Object getPrincipal() {
        return user;
      }

      @Override
      public boolean isAuthenticated() {
        return true;
      }

      @Override
      public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

      }

      @Override
      public String getName() {
        return user.getUsername();
      }
    }));
    org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
  }
}
