package com.example.SpringWebMVC.repository;

import com.example.SpringWebMVC.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAORepository extends JpaRepository<User, Long> {

    public User findUserByemailAndPassword(String email, String password);
    Optional<User> findById(Long id);
}
