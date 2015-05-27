package com.ecom.listy.grocery.todo;

import com.ecom.rest.api.UrlSpace;

public class GroceryToDoApiUrls implements UrlSpace {

	public static final String GROCERY_TODOS		= "/api/GroceryToDo";
	public static final String GROCERY_TODO			= "/api/GroceryToDo/{todoId}";
	public static final String GROCERY_TODO_ITEMS	= "/api/GroceryToDo/{todoId}/GroceryToDoItem";
	public static final String GROCERY_TODO_ITEM	= "/api/GroceryToDo/{todoId}/GroceryToDoItem/{itemId}";
}
