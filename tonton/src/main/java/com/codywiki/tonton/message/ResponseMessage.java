package com.codywiki.tonton.message;

public class ResponseMessage {
    // sign
    public static final String LOGIN_SUCCESS = "로그인 성공입니다.";
    public static final String LOGOUT_SUCCESS = "로그아웃 성공입니다.";
    public static final String SIGN_UP_SUCCESS = "회원가입 성공입니다.";
    public static final String CHANGE_PASSWORD_SUCCESS = "비밀번호 재설정 성공입니다.";
    public static final String POSSIBLE_NICKNAME = "사용가능한 닉네임입니다.";
    public static final String POSSIBLE_EMAIL = "사용가능한 이메일입니다.";
    public static final String REISSUE_TOKEN_SUCCESS = "토큰 재발급 성공입니다.";

    // profile
    public static final String GET_MY_PROFILE_SUCCESS = "내 프로필 가져오기 성공입니다.";
    public static final String GET_PROFILE_SUCCESS = "프로필 가져오기 성공입니다.";

    // article
    public static final String POST_ARTICLE_SUCCESS = "게시글 작성 성공입니다.";
    public static final String READ_ALL_ARTICLES_SUCCESS = "전체 게시글 읽기 성공입니다.";

    private ResponseMessage() {
    }
}
