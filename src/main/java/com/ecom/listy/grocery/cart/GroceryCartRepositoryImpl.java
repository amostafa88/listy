package com.ecom.listy.grocery.cart;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class GroceryCartRepositoryImpl implements GroceryCartRepositoryCustom {

//	@Autowired MongoTemplate mongoTemplate;
//	@Autowired MongoOperations mongoOperations;
	@Autowired
	private GroceryCartRepository groceryCartRepository;

	
	@Override
	public GroceryCartItem saveToCartId(String id,GroceryCartItem groceryCartItem){
		
		//get the Cart
		GroceryCart cart = groceryCartRepository.findOne(id);
		List<GroceryCartItem> cartItemsList = cart.getGroceryCartItems();
		//groceryCartItemRepository.save(groceryCartItem);
		cartItemsList.add(groceryCartItem);
		groceryCartRepository.save(cart);
		return groceryCartItem;//with the id
		
	}
	
	@Override
	public void deleteItemFromCart(String cartId,String itemId){
		//get the Cart
		System.out.println("calling .. delete");
		GroceryCart cart = groceryCartRepository.findOne(cartId);
		List<GroceryCartItem> cartItemsList = cart.getGroceryCartItems();
		cartItemsList.remove(new GroceryCartItem(0,"",itemId));
		
		cart.setGroceryCartItems(cartItemsList);
		groceryCartRepository.save(cart);
	}

}
