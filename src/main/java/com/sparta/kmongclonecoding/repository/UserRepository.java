package com.sparta.kmongclonecoding.repository;

import com.sparta.kmongclonecoding.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.sparta.kmongclonecoding.dto.User, Long> {
    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);

}

