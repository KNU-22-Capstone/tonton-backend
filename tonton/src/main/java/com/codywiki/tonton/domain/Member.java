package com.codywiki.tonton.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String nickname;
    private String loginId;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "member")
    private final List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Zzim> zzims = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Recommend> recommends = new ArrayList<>();

    @Builder
    public Member(final String name,
                  final String nickname,
                  final String loginId,
                  final String password,
                  final String phone) {
        this.name = name;
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
        this.phone = phone;
    }
}
