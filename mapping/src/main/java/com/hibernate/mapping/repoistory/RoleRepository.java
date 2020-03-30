package com.hibernate.mapping.repoistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.mapping.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleName(String string);

}
