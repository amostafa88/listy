package com.ecom.listy.grocery.special;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.listy.grocery.cart.GroceryCart;
import com.ecom.listy.grocery.cart.GroceryCartItem;
import com.ecom.listy.grocery.cart.GroceryCartRepository;
import com.ecom.listy.grocery.item.GroceryItem;
import com.ecom.listy.grocery.item.GroceryItemRepository;

@RestController
@RequestMapping("/api/GrocerySpecial")
public class GrocerySpecialController {
	
	
	@Autowired
	private SpecialRepository specialRepository;

    
    @RequestMapping(value="/ItemsInCart/{id}", method=RequestMethod.GET)
    List<String> itemsInCartId(@PathVariable("id") String id) {
    	return specialRepository.getItemsInCart(id);
	    //return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    

	
	@RequestMapping(value="/AddItemsToCart/{id}", method=RequestMethod.POST)
	public ResponseEntity<Boolean> create(@PathVariable("id") String id, @RequestBody @Valid GroceryCartItem groceryCartItem) {
		specialRepository.saveToCartId(id, groceryCartItem);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
}
