package com.takehome.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takehome.backend.model.TableA;
import com.takehome.backend.repository.TableARepository;

@RestController
@RequestMapping("/api")
public class TableAController {
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	TableARepository tableARepository;
	
	@GetMapping("/getTableAData")
    public String getTableAData() throws JsonProcessingException{

		List<TableA> tableA= new ArrayList<TableA>();
		tableA = tableARepository.findAll();
		
        return mapper.writeValueAsString(tableA);
    }
}
