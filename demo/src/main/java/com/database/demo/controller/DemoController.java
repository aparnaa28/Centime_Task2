package com.database.demo.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.database.demo.aspect.LogMethodParam;
import com.database.demo.entity.DemoTable;
import com.database.demo.service.DemoService;

@RestController
public class DemoController {
	
	
	@Autowired
	private  DemoService demoService;
	
	@PostMapping(path = "/insert")
	public DemoTable insertData(@RequestBody DemoTable record)
	{
		return demoService.saveDetails(record);
	}
	
	
	@GetMapping(path="/getRecord/{id}")
	@LogMethodParam
	public  DemoTable getRecord(@PathVariable int id)
	{
	     Optional<DemoTable> record = demoService.findById(id);
	     
	     if(record.isPresent())
	     {
	    	 DemoTable demoRecord=record.get();
	    	 return demoRecord;
	     }
	     else
	    	 return null;	 
	}
	
	@GetMapping(path="/getList")
	@LogMethodParam
	public String getList()
	{
		return demoService.getList().toString();
	}
}
