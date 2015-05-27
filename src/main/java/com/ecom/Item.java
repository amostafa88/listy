package com.ecom;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecom.Name;
import com.ecom.listy.grocery.category.GroceryCategory;

@Document
public abstract class Item implements Serializable {


	@Id
	protected String id;
	
	@Indexed
	protected Name name;
	
	protected double invQuantity;
	
	protected String description;


	public Item() {
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Item(Name name, double invQuantity, String description) {
		super();
		this.name = name;
		this.invQuantity = invQuantity;
		this.description = description;
	}

	public Item(String id, Name name, double invQuantity, String description) {
		super();
		this.id = id;
		this.name = name;
		this.invQuantity = invQuantity;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", invQuantity="
				+ invQuantity + ", description=" + description
				 + "]";
	}


}
