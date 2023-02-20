package com.database.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DemoTable")
public class DemoTable {
	
	@Id
	@Column(name="Id")
	int id;
	
	@Column(name="ParentId")
	int parentId;
	
	@Column(name="Name")
	String name;
	
	@Column(name="Color")
	String color;
	
	public DemoTable()
	{
		
	}
	
	public DemoTable(int id, int parentId, String name, String color) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.color = color;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	

}
