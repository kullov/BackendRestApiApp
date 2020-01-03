package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.domain.AppRole;
import com.backend.domain.AppUser;
import com.backend.repository.IRoleDAO;
import com.backend.service.IUserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IRoleDAO roleDAO;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping({ "api/roles" })
	public List<AppRole> getAllRoles() {
		return roleDAO.findAll();
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping({ "api/users" })
	public List<AppUser> getAllUsers() {
		List<AppUser> list = userService.findAll();
		for (AppUser appUser : list) {
			System.out.println(appUser.toString());
		}
		return list;
	}
	
	@RequestMapping({ "api/hello" })
	public String firstPage() {
		return "Hello World";
	}
	
	@RequestMapping({ "/403" })
	public String errorPage() {
		return "You do not have permission to access this site!";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("api/user/all")
	public Page<AppUser> getAllUsers(@RequestParam(value="page", defaultValue = "1") int page) {
		Page<AppUser> list = userService.getAllUsers(page-1, 5);
		return list;
	}
	
	@GetMapping("api/user/detail/{id}")
	public AppUser getTaskById(@PathVariable("id") long userId) {
		AppUser user = userService.findByUserId(userId);
		return user;
	}
	
	@PostMapping("/api/user/create")
	public AppUser doSaveCustomer( @RequestBody AppUser user, UriComponentsBuilder builder) {
		boolean flag = userService.addUser(user, "USER");
		if (flag == true) {
			return user;
		} else {
			return null;
		}
	}
	
	@PutMapping("api/user/update/{id}")
	public AppUser updateUser(@RequestBody AppUser user) {
		userService.updateUser(user);
		return user;
	}
	
	@DeleteMapping("api/user/delete/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
	
	@PostMapping("/login")
	  public String login(@RequestBody AppUser user) {
	    String result = "";
	    try {
	      if (userService.checkLogin(user)) {
	        result = user.getUserName();
	      } else {
	        result = "Wrong userId and password";
	      }
	    } catch (Exception ex) {
	      result = "Server Error";
	    }
	    return result;
	  }
	
}
