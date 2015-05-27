package com.ecom.listy.grocery.cart;

import java.util.List;

import com.ecom.Cart;

/**
 * 
 * Holds the grocery carts (lists)  of specific user.
 * 
 * @author amostafa
 *
 */
public class GroceryCart extends Cart {

	List<GroceryCartItem> groceryCartItems;
	

	public GroceryCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroceryCart(boolean status, double quantity, double total,List<GroceryCartItem> groceryCartItems) {
		super(status, quantity, total);
		this.groceryCartItems = groceryCartItems;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "GroceryCart [groceryCartItems=" + groceryCartItems + ", id="
				+ id + ", status=" + status + ", quantity=" + quantity
				+ ", total=" + total + "]";
	}

	public List<GroceryCartItem> getGroceryCartItems() {
		return groceryCartItems;
	}
	
	

	public void setGroceryCartItems(List<GroceryCartItem> groceryCartItems) {
		this.groceryCartItems = groceryCartItems;
	}

}
