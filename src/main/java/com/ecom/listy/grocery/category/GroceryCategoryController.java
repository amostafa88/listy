package com.ecom.listy.grocery.category;


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
public class GroceryCategoryController {
	
	
	@Autowired
	private GroceryCategoryRepository groceryCategoryRepository;
	
	
    @RequestMapping(value=ApiUrls.GROCERY_CATEGORIES,method = RequestMethod.GET)
    List<GroceryCategory> findAll() {
        return groceryCategoryRepository.findAll();
    }
    
    @RequestMapping(value=ApiUrls.GROCERY_CATEGORY , method = RequestMethod.GET)
    void getItem(@PathVariable("categoryId") String categoryId) {
    	groceryCategoryRepository.delete(categoryId);
    }    


}
