package com.database.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.graph.Graph;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.demo.aspect.LogMethodParam;
import com.database.demo.entity.DemoTable;
import com.database.demo.entity.Response;
import com.database.demo.repository.DemoTableRepository;

@Service
public class DemoService {
	

	Response node;
	
	@Autowired
	private DemoTableRepository demoTableRepo;
	
	
	public DemoTable saveDetails(DemoTable demoRecord)
	{
		return demoTableRepo.save(demoRecord);
	}
	
	public Optional<DemoTable> findById(int id)
	{
		return demoTableRepo.findById(id);
	}
	
	public List<DemoTable> fetchRecords()
	{
		return demoTableRepo.findAll();
	}
	
	@LogMethodParam
	void search(List<Response> response, int pid) 
	{
		
		for(Response res:response) 
		{
			
			if(res.getName().getId() == pid) 
			{
				this.node = res;
				return;
			}
			else 
			{
				if(res.getSub_Class()!=null)
					search(res.getSub_Class(),pid);
			}
		}
	}
	
	void responseFormatter(List<Response> response,JSONArray array) 
	{
		for(Response res : response)
		{
		
			JSONObject obj = new JSONObject();
			
			JSONArray new_arr = null;
			
			if(res.getSub_Class() !=null) 
			{	
				new_arr = new JSONArray();
				
				responseFormatter(res.getSub_Class(),new_arr);
			}
			
			if(new_arr !=null)
			{
				obj.put("Sub Classes",new_arr);
				
			}
			
			obj.put("Name", res.getName().getName());
			array.put(obj);
			
		}
		
		
	}
	
	@LogMethodParam
	public JSONArray getList()
	{
		 List<Response> res = new ArrayList<>();
		 List<DemoTable> list=fetchRecords();
	        
	        for (DemoTable d:list)
	        {
	            //Parent Nodes
	            if(d.getParentId() == 0)
	            {
	                Response obj = new Response(d);
	                res.add(obj);
	            }
	            
	            //Direct child
	            else if(d.getParentId() <= res.size())
	            {
	            	Response obj = new Response(d);
	            	
	            	if(res.get(d.getParentId()-1).getSub_Class() == null)
	            		res.get(d.getParentId()-1).setSub_Class(new ArrayList<>());
	            		
	            	
	                res.get(d.getParentId()-1).getSub_Class().add(obj);
	            }
	            
	            //Nested Child
	            else
	            {
	            	search(res,d.getParentId());
	            	Response obj = new Response(d);
	            	
	            	if(node.getSub_Class() == null)
	            		node.setSub_Class(new ArrayList<>());
	            	
	            	node.getSub_Class().add(obj);
	            }
	        }
	        
	        JSONArray arr = new JSONArray();
	        responseFormatter(res,arr);
	        
	      //  System.out.println(arr);
	        
	        return arr;
	        
		}
	
	
}
