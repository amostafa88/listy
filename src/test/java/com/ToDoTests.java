package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecom.ListyApplication;
import com.ecom.Name;
import com.ecom.listy.grocery.category.GroceryCategory;
import com.ecom.listy.grocery.category.GroceryCategoryRepository;
import com.ecom.listy.grocery.item.GroceryItem;
import com.ecom.listy.grocery.item.GroceryItemRepository;
import com.ecom.listy.grocery.todo.GroceryToDoItem;
import com.ecom.listy.grocery.todo.GroceryToDoRepository;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ListyApplication.class)
@WebAppConfiguration
public class ToDoTests {
	
	@Autowired
	private GroceryToDoRepository groceryToDoRepository;

	
	//@Test
	public void unSelectItemTest() {
		
		System.out.println("delete item from a todo list using mongooperation:");
		System.out.println("--------------------------------");
		groceryToDoRepository.unSelectItem("55675c40a594dac769567f43", "55675c40a594dac769567efc");

		
	}

	@Test
	public void selectItemTest() {
		
		System.out.println("delete item from a todo list using mongooperation:");
		System.out.println("--------------------------------");
		
		GroceryToDoItem todoItem = new GroceryToDoItem();
		todoItem.setSelected(true); 
		todoItem.setGroceryItemId("556898a7a594394a243906e6");
		
		groceryToDoRepository.addNewItem("556898a7a594394a24390728", todoItem);

		
	}
}
