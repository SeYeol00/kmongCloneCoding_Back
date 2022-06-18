package com.sparta.kmongclonecoding.repository;

import com.sparta.kmongclonecoding.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
