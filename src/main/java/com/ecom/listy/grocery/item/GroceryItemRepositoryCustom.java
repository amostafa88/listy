package com.ecom.listy.grocery.item;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;

interface GroceryItemRepositoryCustom {

	public List<GroceryItem> getItemsInCart(String id);
}
