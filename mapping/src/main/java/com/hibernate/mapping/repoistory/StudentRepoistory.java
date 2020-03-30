package com.hibernate.mapping.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hibernate.mapping.model.Student;

public interface StudentRepoistory extends JpaRepository<Student, Long> {

}
