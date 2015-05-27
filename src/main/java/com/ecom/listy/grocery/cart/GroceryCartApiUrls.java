package com.ecom.listy.grocery.cart;

import com.ecom.rest.api.UrlSpace;

public class GroceryCartApiUrls implements UrlSpace {

	public static final String GROCERY_CARTS		= "/api/GroceryCart";
	public static final String GROCERY_CART			= "/api/GroceryCart/{cartId}";
	public static final String GROCERY_CART_ITEMS	= "/api/GroceryCart/{cartId}/GroceryCartItem";
	public static final String GROCERY_CART_ITEM	= "/api/GroceryCart/{cartId}/GroceryCartItem/{itemId}";
}
