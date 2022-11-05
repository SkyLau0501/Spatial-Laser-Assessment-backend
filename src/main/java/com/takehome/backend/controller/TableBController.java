package com.takehome.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takehome.backend.model.TableB;
import com.takehome.backend.repository.TableBRepository;

@RestController
@RequestMapping("/api")
public class TableBController {
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	TableBRepository tableBRepository;
	
	@GetMapping("/getTableBData")
    public String getTableBData() throws JsonProcessingException{

		List<TableB> tableB= new ArrayList<TableB>();
		tableB = tableBRepository.findAll();
		
        return mapper.writeValueAsString(tableB);
    }
}
