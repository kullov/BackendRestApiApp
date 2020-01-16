package com.backend.authentication.user_;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.domain.AppUser;
import com.backend.repository.IUserDAO;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserDAO userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUserName(username);
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else if (user != null) {
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
