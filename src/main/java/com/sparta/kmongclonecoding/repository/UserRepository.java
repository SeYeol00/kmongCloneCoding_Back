package com.sparta.kmongclonecoding.repository;

import com.sparta.kmongclonecoding.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByKakaoId(Long kakaoId);

}

