package com.ecom.listy.grocery.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecom.Category;
import com.ecom.Name;


/**
 * 
 * Hold the category of items
 * 
 * @author amostafa
 *
 */

public class GroceryCategory extends Category {

	public GroceryCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroceryCategory(Name name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GroceryCategory [id=" + id + ", name=" + name + "]";
	}



}
