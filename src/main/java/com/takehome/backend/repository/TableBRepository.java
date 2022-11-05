package com.takehome.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takehome.backend.model.TableB;

public interface TableBRepository extends JpaRepository<TableB, Long> {
	List<TableB> findAll();
}