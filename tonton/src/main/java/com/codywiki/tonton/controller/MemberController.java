package com.codywiki.tonton.controller;

import com.codywiki.tonton.controller.dto.ResponseDto;
import com.codywiki.tonton.message.ResponseMessage;
import com.codywiki.tonton.service.MemberService;
import com.codywiki.tonton.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<ResponseDto> findMemberInfoById() {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.GET_MY_PROFILE_SUCCESS,
                memberService.findMemberInfoById(SecurityUtil.getCurrentMemberId())));
    }

    @GetMapping("/{loginId}")
    public ResponseEntity<ResponseDto> findMemberInfoByLoginId(@PathVariable String loginId) {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.GET_PROFILE_SUCCESS,
                memberService.findMemberInfoByLoginId(loginId)));
    }
}
