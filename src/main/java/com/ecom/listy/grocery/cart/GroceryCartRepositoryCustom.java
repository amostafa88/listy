package com.ecom.listy.grocery.cart;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;

interface GroceryCartRepositoryCustom {

	public GroceryCartItem saveToCartId(String id,GroceryCartItem groceryCartItem);
	public void deleteItemFromCart(String cartId,String itemId);
}
