package com.takehome.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takehome.backend.model.TableA;

public interface TableARepository extends JpaRepository<TableA, Long> {
	List<TableA> findAll();
}