package com.ecom.listy.grocery.item;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.listy.grocery.cart.GroceryCart;
import com.ecom.listy.grocery.cart.GroceryCartRepository;

@RestController
public class GroceryItemController {
	
	
	@Autowired
	private GroceryItemRepository groceryItemRepository;
	
	
    @RequestMapping(value=ApiUrls.GROCERY_ITEMS,method = RequestMethod.GET)
    List<GroceryItem> findAll() {
        return groceryItemRepository.findAll();
    }
    
    @RequestMapping(value=ApiUrls.GROCERY_ITEM, method = RequestMethod.GET)
    void getItem(@PathVariable("itemId") String itemId) {
        groceryItemRepository.delete(itemId);
    }    

    


}
