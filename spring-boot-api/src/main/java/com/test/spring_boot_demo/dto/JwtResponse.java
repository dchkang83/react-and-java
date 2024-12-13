package com.test.spring_boot_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
  private String accessToken;
  private String refreshToken;
}