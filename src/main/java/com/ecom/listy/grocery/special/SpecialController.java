package com.ecom.listy.grocery.special;


import java.util.List;

import javax.validation.Valid;

import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.steps.applyOptional;
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
import com.ecom.listy.util.LoadDB_FromExcel;

@RestController
public class SpecialController {
	
	
	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Autowired
	private LoadDB_FromExcel loadDB_FromExcel;
	
	
    @RequestMapping(value=ApiUrls.SPECIAL_RELOAD_DB, method=RequestMethod.GET)
    public ResponseEntity<Boolean> itemsInCartId() {

		System.out.println("reloading database ...");
		loadDB_FromExcel.dbLoads();
	
		System.out.println("reload done, items :"+groceryItemRepository.findAll().size());
		
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    

	
/*	@RequestMapping(value="/AddItemsToCart/{id}", method=RequestMethod.POST)
	public ResponseEntity<Boolean> create(@PathVariable("id") String id, @RequestBody @Valid GroceryCartItem groceryCartItem) {
		specialRepository.saveToCartId(id, groceryCartItem);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}*/
	
}
