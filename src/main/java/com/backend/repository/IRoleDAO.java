package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.domain.AppRole;

@Repository
public interface IRoleDAO extends JpaRepository<AppRole, Integer> {
	AppRole findByRoleName(String roleName);
}
