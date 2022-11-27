package com.codywiki.tonton.domain;

import com.codywiki.tonton.domain.enums.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member")
    private final List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Zzim> zzims = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Recommend> recommends = new ArrayList<>();

    @Builder
    public Member(final String name, final String nickname, final String loginId, final String password,
                  final Authority authority) {
        this.name = name;
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
        this.authority = authority;
    }
}
