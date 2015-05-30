package com.ecom.listy.grocery.todo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.ecom.CartItem;
import com.ecom.ToDoItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * Holds the user selected item, it will contain the item id and extra information, it will be encolsed in a cart.
 * 
 * @author amostafa
 *
 */
public class GroceryToDoItem extends ToDoItem {
	
	
	@NotNull(message = "Cart item can't contain empty item id.")
	//@Length(min=0, message = "Cart item can't contain empty item id." )
	private String groceryItemId;
	
	protected boolean selected;
	
	private String groceryCategoryId;

	

	public GroceryToDoItem() {
		super();
	}

	public GroceryToDoItem(boolean selected, double quantity, String comments,String groceryItemId, String groceryCategoryId) {
		super(quantity, comments);
		this.selected = selected;
		this.groceryItemId = groceryItemId;
		this.groceryCategoryId=groceryCategoryId;
	}


	

	@Override
	public String toString() {
		return "GroceryToDoItem [groceryItemId=" + groceryItemId
				+ ", selected=" + selected + ", groceryCategoryId="
				+ groceryCategoryId + ", quantity=" + quantity + ", comments="
				+ comments + "]";
	}

	public String getGroceryItemId() {
		return groceryItemId;
	}

	public void setGroceryItemId(String groceryItemId) {
		this.groceryItemId = groceryItemId;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.groceryItemId.equals(((GroceryToDoItem) obj).groceryItemId));
	}

	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getGroceryCategoryId() {
		return groceryCategoryId;
	}

	public void setGroceryCategoryId(String groceryCategoryId) {
		this.groceryCategoryId = groceryCategoryId;
	}

}
