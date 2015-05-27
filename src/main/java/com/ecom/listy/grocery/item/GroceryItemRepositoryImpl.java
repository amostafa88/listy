package com.ecom.listy.grocery.item;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ecom.listy.grocery.category.GroceryCategory;

public class GroceryItemRepositoryImpl implements GroceryItemRepositoryCustom {

	@Autowired MongoTemplate mongoTemplate;
	@Autowired MongoOperations mongoOperations;
	
	@Override
	public List<GroceryItem> getItemsInCart(String id) {

		//get the cart from its id
   		 Query q =      new Query(Criteria.where("groceryCategories.name.en").is(id));
   		 List<GroceryItem> q1s = mongoOperations.find(q, GroceryItem.class);

	     return q1s;
	}
	
	
//	
//	@Override
//	public List<GroceryItem> someCustomMethod(String id) {
//		
//   
//	     
//	    		 Query q =      new Query(Criteria.where("groceryCategories.name.en").is(id));
//	    		 
//	    		 
//	    		 List<GroceryItem> q1s = mongoOperations.find(q, GroceryItem.class);
//	     
//
//	     return q1s;
//	}
//	
//	@Override
//	public List<GroceryItem> someCustomMethodPagable(String id, Pageable p) {
//		
//
//		Query q = new Query(Criteria.where("groceryCategories.$id").is(new ObjectId(id)));
//				if (p != null) {
//		            q.with(p);
//		        }
//	     List<GroceryItem> q1s = mongoOperations.find(q, GroceryItem.class);
//
//	     return q1s;
//	}

	
	
	
	
	public List<GroceryItem> getAllForCategory(GroceryCategory cat) {
		Query query = new Query(Criteria.where("groceryCategories.$id").is(new ObjectId(cat.getId())));
		List<GroceryItem> posts = mongoOperations.find(query, GroceryItem.class);
		return posts;
	}
}
