package com.ecom.listy.grocery.cart;


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
public class GroceryCartController {
	
	
	@Autowired
	private GroceryCartRepository groceryCartRepository;
	@Autowired
	private GroceryItemRepository groceryItemRepository;
	
	@Autowired
	private SpecialRepository specialRepository;	
	
    @RequestMapping(value = GroceryCartApiUrls.GROCERY_CARTS,method = RequestMethod.GET)
    List<GroceryCart> findAll() {
        return groceryCartRepository.findAll();
    }
    
    //@RequestMapping(value="/{id}", method=RequestMethod.GET)
    @RequestMapping(value = GroceryCartApiUrls.GROCERY_CART, method = RequestMethod.GET)	
    GroceryCart get(@PathVariable("cartId") String id) {
    	
    	GroceryCart groceryCart= groceryCartRepository.findOne(id);
    	
    	if (groceryCart == null){
    		
    					throw new ResourceNotFoundRestApiException()
    							  .userMessage("Cart '%s' does not exist",id)
    							  .developerMessage("Wrong cart id in the url");
    	}
    	
	    return groceryCartRepository.findOne(id);
    }    

    @RequestMapping(value=GroceryCartApiUrls.GROCERY_CART_ITEMS, method=RequestMethod.PUT)
    public GroceryCartItem addCartItem(@PathVariable("cartId") String id, @RequestBody @Valid GroceryCartItem groceryCartItem) {
//    	System.out.println("received cart id = "+jsonMap.get("id"));
//    	System.out.println("item comments = " +jsonMap.get("comments"));
//    	System.out.println("item quantity = " +jsonMap.get("quantity"));
    	//System.out.println("item id = " +jsonMap.get("quantity"));
    	
    	//groceryItemRepository.findOne(arg0)
    	//GroceryCartItem groceryCartItem = new GroceryCartItem();
    	//Double.parseDouble((String)jsonMap.get("quantity")),
//    			jsonMap.get("comments"),
//    			);
    	//return groceryCartItem;
//    	System.out.println("received cart item comment = " + groceryCartItem.getComments());
    	//System.out.println("saving to cart id = "+groceryCartItem.getId());
    	 groceryCartItem = groceryCartRepository.saveToCartId(id, groceryCartItem);
    	 System.out.println(" item added = " + groceryCartItem.getGroceryItemId());
    	 return groceryCartItem;
	    //return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
    
    
	@RequestMapping(value=GroceryCartApiUrls.GROCERY_CART_ITEM, method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("cartId") String id,@PathVariable("itemId") String itemId) {
		groceryCartRepository.deleteItemFromCart(id,itemId);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}    
	
	
	
}
