package com.backend.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.domain.AppRole;
import com.backend.domain.AppUser;
import com.backend.repository.IRoleDAO;
import com.backend.repository.IUserDAO;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService, IUserService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Override
	public Page<AppUser> getAllUsers(int pageN, int limit) {
		Page<AppUser> page = userDAO.findAll(PageRequest.of(pageN, limit));
		return page;
	}

	@Override
	public AppUser findByUserId(long userId) {
		return userDAO.findById(userId).get();
	}

	@Override
	public boolean addUser(AppUser account, String role) {
		AppUser checkUser = userDAO.findByUserName(account.getUserName());
		if (checkUser != null) {
			return false;
		} else {
			AppRole userRole = roleDAO.findByRoleName(role);
			AppUser user = new AppUser();
	    	String encodedPassword = bCryptPasswordEncoder.encode(account.getPassword());
	    	user.setUserName(account.getUserName());
	    	user.setPassword(encodedPassword);
	    	user.setEmail(account.getEmail());
	    	user.setPhone(account.getPhone());
	    	user.setEnable(1);
	    	user.setRoles(new HashSet<AppRole>(Arrays.asList(userRole)));
//	    	user.setRoles(new HashSet<>(roleDAO.findAll()));
			userDAO.save(user);
			return true;
		}
	}

	@Override
	public void updateUser(AppUser account) {
		AppUser accountDb = userDAO.findById(account.getUserId()).get();
//		accountDb.setUserId(account.getUserId());
		accountDb.setUserName(account.getUserName());
		accountDb.setPassword(account.getPassword());
		accountDb.setEmail(account.getEmail());
		accountDb.setPhone(account.getPhone());
		accountDb.setEnable(account.getEnable());
		userDAO.save(accountDb);
	}

	@Override
	public void deleteUser(long id) {
		AppUser account = userDAO.findById(id).get();
		if (account != null) {
			userDAO.delete(account);
		}
	}

	@Override
	public AppUser findByUserName(String userName) {
		return userDAO.findByUserName(userName);
	}
	
	@Override
	public boolean checkLogin(AppUser user) {
		for (AppUser userExist : userDAO.findAll()) {
	      if (user.getUserName().equalsIgnoreCase(userExist.getUserName())
	          && user.getPassword().equals(userExist.getPassword())) {
	        return true;
	      }
	    }
		return false;
    }

	@Override
	public List<AppUser> findAll() {
		return userDAO.findAllUsers();
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 AppUser user = userDAO.findByUserName(username);

	        if (user == null) {
	            throw new UsernameNotFoundException("User " //
	                    + username + " was not found in the database");
	        }
	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        for (AppRole role : user.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
	        }
//	        user.getRoles().forEach(role -> {
//	        	grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//			});
	        return new org.springframework.security.core.userdetails.User(
	                user.getUserName(), user.getPassword(), grantedAuthorities);
	}
	
}
