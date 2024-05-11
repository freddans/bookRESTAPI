package com.example.bookbook.repositories;

import com.example.bookbook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
}
