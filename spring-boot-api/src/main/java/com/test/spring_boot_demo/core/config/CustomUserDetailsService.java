package com.test.spring_boot_demo.core.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // For testing purposes, create a hardcoded user
    if ("admin".equals(username)) {
      // UserEntity userEntity = userRepository.findByUsername(username);
      return User.builder()
          .username("admin")
          // This is "password" encoded with BCrypt
          .password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
          .roles("ADMIN")
          .build();
    }

    throw new UsernameNotFoundException("User not found: " + username);
  }
}