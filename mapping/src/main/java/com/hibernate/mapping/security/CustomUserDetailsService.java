package com.hibernate.mapping.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.mapping.model.User;
import com.hibernate.mapping.model.UserPrincipal;
import com.hibernate.mapping.repoistory.UserRepoistory;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepoistory userRepoistory;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepoistory.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
      	                   
      return  UserPrincipal.create(user);
	}
	
	@Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepoistory.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        UserDetails userDetails = UserPrincipal.create(user);
        System.out.println("userDetails:"+userDetails.toString());
        return userDetails;
    }

}
