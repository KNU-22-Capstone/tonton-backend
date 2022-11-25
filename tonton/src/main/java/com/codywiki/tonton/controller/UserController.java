package com.codywiki.tonton.controller;

import com.codywiki.tonton.domain.test.User;
import com.codywiki.tonton.dto.UserDto;
import com.codywiki.tonton.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    /**
     * @PreAuthrize 를 통해 USER, ADMIN 두가지 권한 모두 허용
     */
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        System.out.println("call");
        return ResponseEntity.ok(userService.getMyUserWithAuthorities()
                .get());
    }

    /**
     * ADMIN 권한만 호출할 수 있도록 설정
     */
    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        ResponseEntity<User> ok = ResponseEntity.ok(userService.getUserWithAuthorities(username)
                .get());
        log.info("ok = {}", ok);
        return ok;
    }

}
