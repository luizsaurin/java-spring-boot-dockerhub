package com.example.dockerci.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dockerci.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
