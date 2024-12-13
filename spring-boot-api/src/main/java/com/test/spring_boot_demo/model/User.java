package com.test.spring_boot_demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "사용자 정보")
public class User {

  @Schema(description = "사용자 ID", example = "1")
  private Long id;

  @Schema(description = "사용자 이름", example = "홍길동")
  private String name;

  @Schema(description = "이메일", example = "hong@example.com")
  private String email;
}