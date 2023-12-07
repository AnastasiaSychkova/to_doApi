package com.example.to_do_api.repository;

import com.example.to_do_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByMail(String mail);
    Optional<User> findByMail(String mail);
}
