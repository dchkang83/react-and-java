package com.test.spring_boot_demo.core.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.springframework.lang.NonNull;

import com.test.spring_boot_demo.constants.JwtConstants;
import com.test.spring_boot_demo.core.utils.JwtTokenUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  // @Override
  // protected void doFilterInternal(@NonNull HttpServletRequest request,
  // @NonNull HttpServletResponse response,
  // @NonNull FilterChain filterChain) throws ServletException, IOException {
  // String authHeader = request.getHeader(JwtConstants.HEADER_STRING);

  // if (authHeader != null && authHeader.startsWith(JwtConstants.TOKEN_PREFIX)) {
  // String token = authHeader.substring(7);

  // if (jwtTokenUtil.validateToken(token)) {
  // String username = jwtTokenUtil.getUsernameFromToken(token);
  // System.out.println("### doFilterInternal username: " + username);

  // UserDetails userDetails = userDetailsService.loadUserByUsername(username);

  // System.out.println("### doFilterInternal userDetails: " + userDetails);

  // UsernamePasswordAuthenticationToken authentication = new
  // UsernamePasswordAuthenticationToken(userDetails, null,
  // userDetails.getAuthorities());

  // SecurityContextHolder.getContext().setAuthentication(authentication);
  // }
  // }

  // filterChain.doFilter(request, response);
  // }
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    try {
      String authHeader = request.getHeader(JwtConstants.HEADER_STRING);

      if (authHeader != null && authHeader.startsWith(JwtConstants.TOKEN_PREFIX)) {
        String token = authHeader.substring(7);

        try {
          if (jwtTokenUtil.validateToken(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        } catch (io.jsonwebtoken.io.DecodingException e) {
          System.err.println("JWT Token 디코딩 실패: " + e.getMessage());
          System.err.println("Token: " + token);
        } catch (Exception e) {
          System.err.println("JWT Token 처리 중 오류 발생: " + e.getMessage());
        }
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      System.err.println("Filter 처리 중 오류 발생: " + e.getMessage());
      throw e;
    }
  }
}