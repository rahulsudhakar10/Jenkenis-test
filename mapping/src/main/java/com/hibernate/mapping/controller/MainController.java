package com.hibernate.mapping.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.mapping.model.JwtResponse;
import com.hibernate.mapping.model.Role;
import com.hibernate.mapping.model.Student;
import com.hibernate.mapping.model.User;
import com.hibernate.mapping.repoistory.RoleRepository;
import com.hibernate.mapping.repoistory.UserRepoistory;
import com.hibernate.mapping.security.JwtTokenProvider;
import com.hibernate.mapping.service.StudentService;

@RestController
@RequestMapping("/user")
public class MainController {
	
	@Autowired
	UserRepoistory userRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
    RoleRepository roleRepository;
	
	@Autowired
    AuthenticationManager authenticationManager;

	
	@PostMapping("/saveStudent")
	//@PreAuthorize("hasRole('USER')")
	@PreAuthorize("hasAuthority('DASHBOARD')")
	public String saveStudent(@RequestBody Student student) {
		if (student != null) {
			studentService.add(student);
			return "saved Suceesfully";
		}
		else {
			return "Student not available";
		}	
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		if (userRepository.existsByUsername(user.getUsername())) {
			return new ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			return new ResponseEntity("Email Address already in use!", HttpStatus.BAD_REQUEST);
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByRoleName("USER")
				.orElseThrow(() -> new RuntimeException("User Role not set."));
		user.setRoles(Collections.singleton(userRole));

		userRepository.save(user);

		return new ResponseEntity("User registered sucessfully", HttpStatus.OK);

	}
	
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
	

}
