package com.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.backend.domain.AppUser;

public interface IUserService {
	
	Page<AppUser> getAllUsers(int page, int limit);
	
	AppUser findByUserName(String userName);
	
	AppUser findByUserId(final long userId);
	
	boolean addUser(AppUser account, String role);

	void updateUser(AppUser user);

	void deleteUser(long id);

	boolean checkLogin(AppUser user);

	List<AppUser> findAll();
}
