package com.codywiki.tonton.repository;

import com.codywiki.tonton.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // for JWT
    Optional<Member> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);

}
