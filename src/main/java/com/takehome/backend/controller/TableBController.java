package com.takehome.backend.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.takehome.backend.PublicString;
import com.takehome.backend.model.PlaceKey;
import com.takehome.backend.model.TableA;
import com.takehome.backend.model.TableB;
import com.takehome.backend.repository.TableARepository;
import com.takehome.backend.repository.TableBRepository;

@RestController
@RequestMapping("/api")
public class TableBController {
	
	ObjectMapper mapper = new ObjectMapper();
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	TableBRepository tableBRepository;

	@Autowired
	TableARepository tableARepository;
	
	@GetMapping("/getTableBData")
    public List<TableB> getTableBData() throws JsonProcessingException{

		List<TableB> tableB= new ArrayList<TableB>();
		tableB = tableBRepository.findAll();
		
		return tableB;
    }

	@GetMapping("/removeDuplicate")
    public List<TableB> removeDuplicate() throws JsonProcessingException{

		List<TableA> tableA = new ArrayList<TableA>();
		tableA = tableARepository.findAll();
		List<TableB> tableB = getTableBData();
		
		List<String> tableAKey = new LinkedList<>();
		List<String> tableBKey = new LinkedList<>();

		//get TableA placekey
		if(tableA.size() > 0){
			for(TableA tableARecord : tableA) {
				String jsonString = callPlaceKeyAPI(tableARecord.getAddress(),tableARecord.getCity(),tableARecord.getState());
				PlaceKey pk = mapper.readValue(jsonString,PlaceKey.class);
				tableAKey.add(pk.getPlacekey());
			}
		}

		//get TableB placekey
		if(tableB.size() > 0){
			for (TableB tableBRecord : tableB){
				String jsonString = callPlaceKeyAPI(tableBRecord.getAddress(),tableBRecord.getCity(),tableBRecord.getState());
				PlaceKey pk = mapper.readValue(jsonString,PlaceKey.class);
				tableBKey.add(pk.getPlacekey());
			}
		}

		//compare TableA and TableB placekey, if there is duplicate, remove the tableB from the list
		if(tableBKey.size() > 0){
			for(int i = 0;i < tableBKey.size();i++) {
				if(tableAKey.contains(tableBKey.get(i))) tableB.set(i, null);
			}
		}
		
		while (tableB.remove(null));

		return tableB;
    }

	public String callPlaceKeyAPI(String address, String city, String state) {
		String url = "https://api.placekey.io/v1/placekey";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("apikey", PublicString.apikey);
		ObjectNode subJson = mapper.createObjectNode();
		subJson.put("street_address", address);
		subJson.put("city", city);
		subJson.put("region", state);
		subJson.put("postal_code", "");
		subJson.put("iso_country_code", "US");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{\"query\":");
		stringBuilder.append(subJson.toString());
		stringBuilder.append("}");
		
		HttpEntity<String> request = new HttpEntity<String>(stringBuilder.toString(), headers);
		String result = "";

		try {
			result = restTemplate.postForObject(url, request,String.class);
		}
		catch (Exception e)
		{
			return "Fail to get placeKey API";
		}
		

		return result;

	}
}
