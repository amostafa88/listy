package com.ecom.listy.grocery.item;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecom.Item;
import com.ecom.Name;
import com.ecom.listy.grocery.category.GroceryCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Holds the whole list of the items managed by the application (not specific user cart specific) 
 * 
 * @author amostafa
 *
 */
public class GroceryItem extends Item {


	@DBRef 
	private List<GroceryCategory> groceryCategories;
	
	public List<GroceryCategory> getGroceryCategories() {
		return groceryCategories;
	}

	public void setGroceryCategories(List<GroceryCategory> categories) {
		this.groceryCategories = categories;
	}
	
	public GroceryItem() {
		super();
	}

	public GroceryItem(Name name, double invQuantity,
			String description, List<GroceryCategory> groceryCategories) {
		super(name, invQuantity, description);
		this.groceryCategories = groceryCategories;
	}
	
	@Override
	public String toString() {
		return "GroceryItem [id="+ id + ", name=" + name + ", invQuantity=" + invQuantity
				+ ", description=" + description + ", groceryCategories=" + groceryCategories + ", ]";
	}




}
