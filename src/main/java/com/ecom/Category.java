package com.ecom;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class  Category implements Serializable{

	@Id
    protected String id;
	
	@Indexed
	protected Name name;
	
	
	
	public Category(Name name) {
		super();
		this.name = name;
		//this.groceryItems = groceryItems;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name
				+ "]";
	}
}
