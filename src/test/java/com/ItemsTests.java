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

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ListyApplication.class)
@WebAppConfiguration
public class ItemsTests {
	
	@Autowired
	private GroceryCategoryRepository groceryCategoryRepository;
	@Autowired
	private GroceryItemRepository groceryItemRepository;
	
	@Test
	public void itemsTest() {
		
		System.out.println("Items found :");
		System.out.println("--------------------------------");
		//List<GroceryItem> groceryItems = groceryItemRepository.findAll();
		//List<GroceryItem> groceryItems = groceryItemRepository.someCustomMethod("x");
		
		List<GroceryItem> groceryItems = groceryItemRepository.findByCatId("5540b69ed6c835ef2875af54");
		
//		GroceryCategory x = groceryCategoryRepository.findByNameEn("x");
//		List<GroceryItem> groceryItems = groceryItemRepository.getAllForCategory(x);
		
		for (GroceryItem item : groceryItems) {
			System.out.println(item);
		}
		
	}

}
