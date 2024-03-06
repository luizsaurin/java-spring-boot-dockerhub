package com.example.githubactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.githubactions.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
