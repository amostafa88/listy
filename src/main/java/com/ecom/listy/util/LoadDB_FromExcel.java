package com.ecom.listy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scala.annotation.meta.getter;

import com.ecom.ListyApplication;
import com.ecom.Name;
import com.ecom.listy.grocery.cart.GroceryCart;
import com.ecom.listy.grocery.cart.GroceryCartItem;
import com.ecom.listy.grocery.cart.GroceryCartRepository;
import com.ecom.listy.grocery.category.GroceryCategory;
import com.ecom.listy.grocery.category.GroceryCategoryRepository;
import com.ecom.listy.grocery.item.GroceryItem;
import com.ecom.listy.grocery.item.GroceryItemRepository;
import com.ecom.listy.grocery.todo.GroceryToDo;
import com.ecom.listy.grocery.todo.GroceryToDoItem;
import com.ecom.listy.grocery.todo.GroceryToDoRepository;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ListyApplication.class)
@WebAppConfiguration
@Component
public class LoadDB_FromExcel {
	
	@Value("classpath:Items-index.xlsx")
	private Resource excelFile;
	//private static final String FILE_PATH = "Items-index.xlsx";
	
	@Autowired
	private GroceryItemRepository groceryItemRepository;
	
	@Autowired
	private GroceryCategoryRepository groceryCategoryRepository;

	@Autowired
	private GroceryCartRepository groceryCartRepository;
	
	@Autowired
	private GroceryToDoRepository groceryToDoRepository;	
	
//	@Autowired
//	private GroceryCartItemRepository groceryCartItemRepository;
	
	
	
	@Test
	public void dbLoads() {
		
		//System.out.println("Items found=" + groceryItemRepository.findAll().size());
		
		//inserting test data:
		System.out.println("------------------------------- Deleteing old data");
		groceryItemRepository.deleteAll();
		groceryCategoryRepository.deleteAll();
		groceryCartRepository.deleteAll();
		groceryToDoRepository.deleteAll();
		//groceryCartItemRepository.deleteAll();
		System.out.println("Deleted.");
		
		// save a couple of items
		System.out.println("------------------------------- Saving fresh data loaded from Excel file");

		
		GroceryItem tomato=null,apple=null,grape=null;
		
		
        List<Category> categoryList = ReadExcelFile.getCategoryListsFromExcel(excelFile,4);

        for (Category cat : categoryList){
        	System.out.println("--------------- Saving Category : "+cat.getName_en());
        	
    		GroceryCategory mongoGroceryCategory = new GroceryCategory(new Name(cat.getName_en(),cat.getName_ar()));
    		List<GroceryCategory> catList = new ArrayList<GroceryCategory>(Arrays.asList(mongoGroceryCategory));
    		
    		groceryCategoryRepository.save(mongoGroceryCategory);
    		for (Item catItem : cat.getItems()){
    			GroceryItem montoItem = new GroceryItem(new Name(catItem.getName_en(),catItem.getName_ar()),catItem.getInvQuantity(),catItem.getDescription(), catList );
    			groceryItemRepository.save(montoItem);
    			
    			//to make a test grocery todo and cart, we will keep referance to tomato , apple, grape
    			if(catItem.getName_en().equals("Apples"))
    				apple=montoItem;
    			if(catItem.getName_en().equals("Grapes"))
    				grape=montoItem;
    			if(catItem.getName_en().equals("Tomatoes"))
    				tomato=montoItem;
    				
    		}
        	
        }
		
			
			
		//now load a Grocery Carts with items
			GroceryCartItem gItem = new GroceryCartItem(2,"only red sold ones, no yellow",tomato.getId());
			GroceryCartItem gItem1 = new GroceryCartItem(2,"Green apple",apple.getId());
		GroceryCart gCart = new GroceryCart(true, 2,4,new ArrayList<GroceryCartItem>(Arrays.asList(gItem,gItem1)));
			//groceryCartItemRepository.save(gItem);
			//groceryCartItemRepository.save(gItem1);
		groceryCartRepository.save(gCart);

			//now load a Grocery Cart with items
			GroceryCartItem gItem3 = new GroceryCartItem(2,"only red sold ones, no yellow",apple.getId());
			GroceryCartItem gItem2 = new GroceryCartItem(2,"Green apple",grape.getId());
		GroceryCart gCart2 = new GroceryCart(true, 2,4,new ArrayList<GroceryCartItem>(Arrays.asList(gItem3,gItem2)));
			//groceryCartItemRepository.save(gItem3);
			//groceryCartItemRepository.save(gItem2);
		groceryCartRepository.save(gCart2);		
		
		
		

		//now load a Grocery ToDo with items
			GroceryToDoItem todoItem = new GroceryToDoItem(true,2,"only red sold ones, no yellow",tomato.getId(),tomato.getGroceryCategories().get(0).getId());
			GroceryToDoItem todoItem1 = new GroceryToDoItem(false,2,"Green apple",apple.getId(),apple.getGroceryCategories().get(0).getId());
		GroceryToDo gToDo = new GroceryToDo(true, 2,4,new ArrayList<GroceryToDoItem>(Arrays.asList(todoItem,todoItem1)));
			//groceryToDoItemRepository.save(todoItem);
			//groceryToDoItemRepository.save(todoItem1);
		groceryToDoRepository.save(gToDo);
	
/*			//now load a Grocery Cart with items
			GroceryToDoItem todoItem3 = new GroceryToDoItem(true,2,"only red sold ones, no yellow",apple.getId(),apple.getGroceryCategories().get(0).getId());
			GroceryToDoItem todoItem4 = new GroceryToDoItem(true,2,"Green apple",grape.getId(),grape.getGroceryCategories().get(0).getId());
		GroceryToDo gToDo2 = new GroceryToDo(true, 2,4,new ArrayList<GroceryToDoItem>(Arrays.asList(todoItem3,todoItem4)));
			//groceryCartItemRepository.save(gItem3);
			//groceryCartItemRepository.save(gItem2);
		groceryToDoRepository.save(gToDo2);	*/	
		
		
		System.out.println("Saved");
		
		// fetch all Items
//		System.out.println("Items found with findAll():");
//		System.out.println("-------------------------------");
//		for (GroceryItem item : this.groceryItemRepository.findAll()) {
//			System.out.println(item);
//		}
//		System.out.println();
//
//		// fetch an individual item
//		System.out.println("Grocery Items found with findByNameEn('Tomato'):");
//		System.out.println("--------------------------------");
//
//        Pageable p = new PageableImp();
//        Page expectedPage = groceryItemRepository.findByNameEn("Tomato",p);
//		System.out.println(expectedPage.getContent());

//		System.out.println("Grocery Categories found with findByNameEn('Vegetables'):");
//		System.out.println("--------------------------------");
//		System.out.println(groceryCategoryRepository.findByNameEn("Vegetables"));
		
	}
	
	
}
