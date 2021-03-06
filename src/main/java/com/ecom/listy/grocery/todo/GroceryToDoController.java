package com.ecom.listy.grocery.todo;


import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.listy.grocery.item.GroceryItemRepository;
import com.ecom.listy.grocery.special.SpecialRepository;
import com.ecom.rest.api.exceptions.ResourceNotFoundRestApiException;


@RestController
//@RequestMapping("/api/GroceryCart")
public class GroceryToDoController {
	
	
	@Autowired
	private GroceryToDoRepository groceryToDoRepository;
	@Autowired
	private GroceryItemRepository groceryItemRepository;
	
	@Autowired
	private SpecialRepository specialRepository;	
	
    @RequestMapping(value = GroceryToDoApiUrls.GROCERY_TODOS,method = RequestMethod.GET)
    List<GroceryToDo> findAll() {
        return groceryToDoRepository.findAll();
    }
    
    @RequestMapping(value = GroceryToDoApiUrls.GROCERY_TODO, method = RequestMethod.GET)	
    GroceryToDo get(@PathVariable("todoId") String id) {
    	
    	GroceryToDo groceryToDo= groceryToDoRepository.findOne(id);
    	
    	if (groceryToDo == null){
    		
    					throw new ResourceNotFoundRestApiException()
    							  .userMessage("ToDo '%s' does not exist",id)
    							  .developerMessage("Wrong ToDo id in the url");
    	}
    	
	    return groceryToDoRepository.findOne(id);
    }
    
    @RequestMapping(value = GroceryToDoApiUrls.GROCERY_TODO, method = RequestMethod.PUT)	
    public ResponseEntity<Boolean> clearSelectedItems(@PathVariable("todoId") String id) {
    	
    	//Clearing selected item from this TODO
    	System.out.println("Clearing ToDo id : "+ id);
    	groceryToDoRepository.clearSelectedItems(id);
    	System.out.println(" Cleared ");
    	return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }       

    @RequestMapping(value=GroceryToDoApiUrls.GROCERY_TODO_ITEMS, method=RequestMethod.POST)
    public GroceryToDoItem addToDoItem(@PathVariable("todoId") String id, @RequestBody @Valid GroceryToDoItem groceryToDoItem) {

    	
    	System.out.println("groceryToDoItem : "+ groceryToDoItem.toString());
    	groceryToDoItem = groceryToDoRepository.addNewItem(id, groceryToDoItem);
    	 System.out.println(" item added = " + groceryToDoItem.getGroceryItemId());
    	 return groceryToDoItem;
    }
    
    @RequestMapping(value=GroceryToDoApiUrls.GROCERY_TODO_ITEMS, method=RequestMethod.PUT)
    public ResponseEntity<Boolean>  updateToDoItem(@PathVariable("todoId") String id, @RequestBody @Valid GroceryToDoItem groceryToDoItem) {

    	groceryToDoRepository.updateItem(id, groceryToDoItem);
    	 System.out.println(" item added = " + groceryToDoItem.getGroceryItemId());
    	 return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
//	@RequestMapping(value=GroceryToDoApiUrls.GROCERY_TODO_ITEM, method=RequestMethod.DELETE)
//	public ResponseEntity<Boolean> delete(@PathVariable("todoId") String id,@PathVariable("itemId") String itemId) {
//		groceryToDoRepository.unSelectItem(id,itemId);
//		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
//	}    
	
	
	
}
