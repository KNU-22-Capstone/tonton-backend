package com.codywiki.tonton.controller;

import com.codywiki.tonton.controller.dto.ResponseDto;
import com.codywiki.tonton.controller.dto.jwt.TokenRequestDto;
import com.codywiki.tonton.controller.dto.member.sign.MemberSignInRequestDto;
import com.codywiki.tonton.controller.dto.member.sign.MemberSignUpRequestDto;
import com.codywiki.tonton.message.ResponseMessage;
import com.codywiki.tonton.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/signout")
    public ResponseEntity<ResponseDto> signOut(@RequestHeader final String authorization) {
        authService.signOut(authorization);
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.LOGOUT_SUCCESS
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signUp(@RequestBody final MemberSignUpRequestDto signUpDto) {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.SIGN_UP_SUCCESS,
                authService.signUp(signUpDto.toServiceDto())));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> signIn(@RequestBody final MemberSignInRequestDto signInDto) {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.LOGIN_SUCCESS,
                authService.signIn(signInDto.toServiceDto())
        ));
    }

    @PostMapping("/reissue")
    public ResponseEntity<ResponseDto> reissue(@RequestBody final TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.REISSUE_TOKEN_SUCCESS,
                authService.reissue(tokenRequestDto.toServiceDto())));
    }
}
