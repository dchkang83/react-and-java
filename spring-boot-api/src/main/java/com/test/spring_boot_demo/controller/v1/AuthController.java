package com.test.spring_boot_demo.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.test.spring_boot_demo.constants.JwtConstants;
import com.test.spring_boot_demo.core.utils.JwtTokenUtil;
import com.test.spring_boot_demo.dto.JwtResponse;
import com.test.spring_boot_demo.dto.LoginRequest;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private AuthenticationManager authenticationManager;

  /*
   * @PostMapping("/login")
   * public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
   * try {
   * Authentication authentication = authenticationManager.authenticate(
   * new UsernamePasswordAuthenticationToken(
   * loginRequest.getUsername(),
   * loginRequest.getPassword()));
   * 
   * SecurityContextHolder.getContext().setAuthentication(authentication);
   * 
   * String accessToken =
   * jwtTokenUtil.generateAccessToken(loginRequest.getUsername());
   * String refreshToken =
   * jwtTokenUtil.generateRefreshToken(loginRequest.getUsername());
   * 
   * return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
   * } catch (AuthenticationException e) {
   * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   * }
   * }
   */

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()));

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    String accessToken = jwtTokenUtil.generateAccessToken(userDetails.getUsername());
    String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails.getUsername());

    return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshToken(@RequestHeader(JwtConstants.REFRESH_TOKEN_HEADER) String refreshToken) {
    if (jwtTokenUtil.validateToken(refreshToken)) {
      String username = jwtTokenUtil.getUsernameFromToken(refreshToken);
      String newAccessToken = jwtTokenUtil.generateAccessToken(username);

      return ResponseEntity.ok(new JwtResponse(newAccessToken, refreshToken));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}