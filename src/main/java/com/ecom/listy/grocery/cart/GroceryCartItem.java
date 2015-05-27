package com.ecom.listy.grocery.cart;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.ecom.CartItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * Holds the user selected item, it will contain the item id and extra information, it will be encolsed in a cart.
 * 
 * @author amostafa
 *
 */
public class GroceryCartItem extends CartItem {
	
	//@DBRef
	@NotNull(message = "Cart item can't contain empty item id.")
	//@Length(min=0, message = "Cart item can't contain empty item id." )
	private String groceryItemId;


	public GroceryCartItem() {
		super();
	}

	public GroceryCartItem(double quantity, String comments,String groceryItemId) {
		super(quantity, comments);
		this.groceryItemId = groceryItemId;
	}


	@Override
	public String toString() {
		return "GroceryCartItem [groceryItemId=" + groceryItemId
				+ ", quantity=" + quantity + ", comments=" + comments + "]";
	}
	
	

	public String getGroceryItemId() {
		return groceryItemId;
	}

	public void setGroceryItemId(String groceryItemId) {
		this.groceryItemId = groceryItemId;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.groceryItemId.equals(((GroceryCartItem) obj).groceryItemId));
	}
	

}
