package com.rafoalfaro.server.repository;

import com.rafoalfaro.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User findUserByUsername(String username);
}
