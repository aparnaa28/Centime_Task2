package com.database.demo.entity;

import java.util.ArrayList;

public class Response {
	
	private DemoTable Name;
    private ArrayList<Response> Sub_Class;
	
	public Response(DemoTable d)
	{
	    this.Name = d;
	}
	
	
	public DemoTable getName() {
		return Name;
	}

	public void setName(DemoTable name) {
		Name = name;
	}

	public ArrayList<Response> getSub_Class() {
		return Sub_Class;
	}

	public void setSub_Class(ArrayList<Response> sub_Class) {
		Sub_Class = sub_Class;
	}

	

	
}	
