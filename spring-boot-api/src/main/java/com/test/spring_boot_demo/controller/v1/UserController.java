package com.test.spring_boot_demo.controller.v1;

import com.test.spring_boot_demo.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@Tag(name = "User", description = "사용자 API")
public class UserController {

    @Operation(summary = "사용자 목록 조회", description = "모든 사용자 정보를 조회합니다.")
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>();
    }

    @Operation(summary = "사용자 조회", description = "특정 사용자의 정보를 조회합니다.")
    @GetMapping("/{id}")
    public User getUser(
            @Parameter(description = "사용자 ID") @PathVariable Long id) {
        return new User();
    }

    @Operation(summary = "사용자 등록", description = "새로운 사용자를 등록합니다.")
    @PostMapping
    public User createUser(
            @Parameter(description = "사용자 정보") @RequestBody User user) {
        return user;
    }

    @Operation(summary = "사용자 정보 수정", description = "기존 사용자의 정보를 수정합니다.")
    @PutMapping("/{id}")
    public User updateUser(
            @Parameter(description = "사용자 ID") @PathVariable Long id,
            @Parameter(description = "수정할 사용자 정보") @RequestBody User user) {
        return user;
    }

    @Operation(summary = "사용자 삭제", description = "특정 사용자를 삭제합니다.")
    @DeleteMapping("/{id}")
    public void deleteUser(
            @Parameter(description = "사용자 ID") @PathVariable Long id) {
    }
}