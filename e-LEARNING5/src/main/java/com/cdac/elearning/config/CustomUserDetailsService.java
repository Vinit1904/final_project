package com.cdac.elearning.config;


import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdac.elearning.model.User;
import com.cdac.elearning.repository.userRepository;
 
@Service
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private userRepository userRepo;
     
    @Override
    public UserDetails loadUserByUsername(String emailId) {
    		User domainUser = userRepo.findByEmail(emailId);
    		if (domainUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
    		   		
    	 return new org.springframework.security.core.userdetails.User(domainUser.getEmailId(),
    			 domainUser.getPassword(),
                 true, true, true, true,
                 getAuthorities(domainUser.getRole()));
    }
    	


    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}