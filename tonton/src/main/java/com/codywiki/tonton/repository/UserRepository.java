package com.codywiki.tonton.repository;

import com.codywiki.tonton.domain.test.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * username을 기준으로 User정보를 가져올때 권한 정보도 같이 가져온다.
     * @EntityGraph 는 쿼리가 수행이 될때 Lazy조회가 아니고 Eager 조회로 authorities 정보를 같이 가져온다.
     */
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
