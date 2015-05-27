package com.ecom.listy.grocery.todo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;

interface GroceryToDoRepositoryCustom {

	public GroceryToDoItem saveToToDoId(String id,GroceryToDoItem groceryToDoItem);
	public void deleteItemFromToDo(String toDoId,String itemId);
}
