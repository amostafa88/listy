package com.ecom.listy.grocery.special;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ecom.listy.grocery.cart.GroceryCart;
import com.ecom.listy.grocery.cart.GroceryCartItem;
import com.ecom.listy.grocery.cart.GroceryCartRepository;

@Component
public class SpecialRepository {

	@Autowired MongoTemplate mongoTemplate;
	@Autowired MongoOperations mongoOperations;
	
	@Autowired
	private GroceryCartRepository groceryCartRepository;
//	@Autowired
//	private GroceryCartItemRepository groceryCartItemRepository;
	
	public List<String> getItemsInCart(String id) {

		//get the cart from its id
		GroceryCart cart = groceryCartRepository.findOne(id);
		
		List<String> cartGroceryItemsIds = null;
		
		if (cart != null && cart.getGroceryCartItems().size() != 0){
			cartGroceryItemsIds = new ArrayList<String>();
			for (GroceryCartItem cartItem : cart.getGroceryCartItems()){
				cartGroceryItemsIds.add(cartItem.getGroceryItemId());
			}
		}
		

     return cartGroceryItemsIds;
	}
	
	
	
	
	public GroceryCartItem saveToCartId(String id,GroceryCartItem groceryCartItem){
		
		//get the Cart
		GroceryCart cart = groceryCartRepository.findOne(id);
		List<GroceryCartItem> cartItemsList = cart.getGroceryCartItems();
//		groceryCartItemRepository.save(groceryCartItem);
		cartItemsList.add(groceryCartItem);
		groceryCartRepository.save(cart);
		return groceryCartItem;//with the id
		
	}
	
	
}
