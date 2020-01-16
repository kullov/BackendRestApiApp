package com.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.domain.AppUser;

@Repository
public interface IUserDAO extends JpaRepository<AppUser, Long> {
	
	public Page<AppUser> findAll(Pageable pageable);
	
	@Query("SELECT t FROM AppUser t")
	public List<AppUser> findAllUsers();
	
	public AppUser findByUserName(String username);
	
	public AppUser findByEmail(String email);
	
	public AppUser findByPhone(String phone);
	
}
