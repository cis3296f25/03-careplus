package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserDetails;

public interface IUserDetailsRepository extends JpaRepository<UserDetails, Integer> {
	public UserDetails findByEmailAndPassword(String email, String pwd);

	public UserDetails findByNameAndEmail(String name, String email);
}
