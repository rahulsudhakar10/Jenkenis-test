package com.hibernate.mapping.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hibernate.mapping.model.Student;
import com.hibernate.mapping.repoistory.StudentRepoistory;
import com.hibernate.mapping.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepoistory studentRepoistory;
 
	public void add(Student student) {
		studentRepoistory.save(student);
    }
}
