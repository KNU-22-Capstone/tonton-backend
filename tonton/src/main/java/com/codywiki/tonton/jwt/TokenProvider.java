package com.codywiki.tonton.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


/**
 * 토큰의 생성, 토큰의 유효성 검증 들을 담당
 * <p>
 * InitializingBean을 implements해서 afterPropertiesSet을 Override한 이유 빈이 생성이 되고 주입을 받은 후에 secret값을 BASE64 Decode한 후 key 변수에
 * 할당하기 위함
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {
    private final static String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") final String secret,
            @Value("${jwt.token-validity-in-seconds}") final long tokenValidityInMilliseconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64
                .decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Authentication객체의 권한정보를 이용해서 토큰을 생성하는 createToken 메소드 추가
     */
    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = new Date().getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /**
     * Token을 받아와 담겨있는 정보를 이용해 Authentication 객체를 리턴하는 메소드
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY)
                        .toString()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * 토큰의 유효성 검증을 수행하는 validatToken 메소드
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException exception) {
            log.info("잘못된 JWT 서명입니다. : {}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            log.info("만료된 JWT 토큰입니다. : {}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.info("지원되지 않는 JWT 토큰입니다. : {}", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.info("JWT 토큰이 잘못되었습니다. : {}", exception.getMessage());
        }
        return false;
    }

}
