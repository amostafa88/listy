package com.ecom.listy.grocery.todo;

import java.util.List;

import com.ecom.ToDo;

/**
 * 
 * Holds the grocery ToDos (lists)  of specific user.
 * 
 * @author amostafa
 *
 */
public class GroceryToDo extends ToDo {


	List<GroceryToDoItem> groceryToDoItems;
	

	public GroceryToDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroceryToDo(boolean status, double quantity, double total,List<GroceryToDoItem> groceryToDoItems) {
		super(status, quantity, total);
		this.groceryToDoItems = groceryToDoItems;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "GroceryToDo [groceryToDoItems=" + groceryToDoItems + ", id="
				+ id + ", status=" + status + ", quantity=" + quantity
				+ ", total=" + total + "]";
	}

	public List<GroceryToDoItem> getGroceryToDoItems() {
		return groceryToDoItems;
	}
	
	

	public void setGroceryToDoItems(List<GroceryToDoItem> groceryToDoItems) {
		this.groceryToDoItems = groceryToDoItems;
	}

}
