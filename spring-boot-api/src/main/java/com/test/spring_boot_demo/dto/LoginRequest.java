package com.test.spring_boot_demo.dto;

import lombok.Data;

@Data
public class LoginRequest {
  private String username;
  private String password;
}