package com.ecom.listy.grocery.todo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;

interface GroceryToDoRepositoryCustom {

	public GroceryToDoItem addNewItem(String id,GroceryToDoItem groceryToDoItem);
	public void updateItem(String toDoId,GroceryToDoItem groceryToDoItem);
	
	
	public void clearSelectedItems(String toDoId);
}
