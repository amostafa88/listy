package com.ecom.listy.grocery.todo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class GroceryToDoRepositoryImpl implements GroceryToDoRepositoryCustom {

//	@Autowired MongoTemplate mongoTemplate;
	@Autowired 
	MongoOperations mongoOperations;
	
	@Autowired
	private GroceryToDoRepository groceryToDoRepository;

	
	@Override
	public GroceryToDoItem addNewItem(String toDoId,GroceryToDoItem groceryToDoItem){
	
		Criteria criteriaV1 = Criteria.where("id").is(toDoId);
		Criteria criteriaV2 = Criteria.where("groceryToDoItems.groceryItemId").is(groceryToDoItem.getGroceryItemId());
		
	    Query findQuery = 
	    Query.query(new Criteria().andOperator(criteriaV1,criteriaV2));
		
 	    //this will update only the selected field to false.
	    Update update = new Update().set("groceryToDoItems.$.selected", true);
	    
	    //groceryToDoItem.setSelected(true);
	    //Update update = new Update().addToSet("groceryToDoItems",groceryToDoItem);
	    
	    // execute the update
	    //WriteResult wr = mongoOperations.updateFirst(findQuery, update, GroceryToDo.class);
	    //mongoOperations.findAndModify(findQuery, update, FindAndModifyOptions.options().upsert(true), GroceryToDo.class);
	    
	    //first lookup the item on the above critiera if found will be updated, and the return will be the grocery to do item.
	    //GroceryToDo temp = mongoOperations.findAndModify(findQuery, update, GroceryToDo.class);
	    //WriteResult wr = mongoOperations.updateFirst(findQuery, update, GroceryToDo.class);
	    //System.out.println(wr.toString());	    
	    
	    //null means that the item not found so we will insert it:
	    //if (temp == null){
		    //groceryToDoItem.setSelected(true);
	    	findQuery = new Query(Criteria.where("id").is(toDoId));
	    	groceryToDoItem.setSelected(true);
		    update = new Update().addToSet("groceryToDoItems",groceryToDoItem);
		    GroceryToDo temp = mongoOperations.findAndModify(findQuery, update, GroceryToDo.class);
	    //} else {
	    //	System.out.println("item found");
	    //}
	    
	    
	    return groceryToDoItem;//with the id
		
	}
	
	
	@Override
	public void updateItem(String toDoId,GroceryToDoItem groceryToDoItem){
	
		Criteria criteriaV1 = Criteria.where("id").is(toDoId);
		Criteria criteriaV2 = Criteria.where("groceryToDoItems.groceryItemId").is(groceryToDoItem.getGroceryItemId());
		
	    Query findQuery = 
	    Query.query(new Criteria().andOperator(criteriaV1,criteriaV2));
		
 	    //this will update only the selected field to false.
	    System.out.println("the item new status received "+ groceryToDoItem.isSelected());
	    Update update = new Update().set("groceryToDoItems.$.selected", groceryToDoItem.isSelected());
	    
	    //groceryToDoItem.setSelected(true);
	    //Update update = new Update().addToSet("groceryToDoItems",groceryToDoItem);
	    
	    // execute the update
	    //WriteResult wr = mongoOperations.updateFirst(findQuery, update, GroceryToDo.class);
	    //mongoOperations.findAndModify(findQuery, update, FindAndModifyOptions.options().upsert(true), GroceryToDo.class);
	    
	    //first lookup the item on the above critiera if found will be updated, and the return will be the grocery to do item.
	    GroceryToDo temp = mongoOperations.findAndModify(findQuery, update, GroceryToDo.class);
	    //WriteResult wr = mongoOperations.updateFirst(findQuery, update, GroceryToDo.class);
	    //System.out.println(wr.toString());	    
	    
	    //null means that the item not found so we will insert it:
	    if (temp == null){
		    //groceryToDoItem.setSelected(true);
	    	System.out.println("not found to update!!");
	    } else {
	    	System.out.println("item found");
	    }
	    
	    
	    //return groceryToDoItem;//with the id
		
	}
	
	
	
	@Override
	public void unSelectItem(String toDoId,String itemId){
		
		//get the Cart
/*		System.out.println("calling .. delete");
		GroceryToDo toDo = groceryToDoRepository.findOne(toDoId);
		List<GroceryToDoItem> toDoItemsList = toDo.getGroceryToDoItems();
		toDoItemsList.remove(new GroceryToDoItem(false,0,"",itemId,""));
		
		toDo.setGroceryToDoItems(toDoItemsList);
		groceryToDoRepository.save(toDo);*/
		
		///
		
	    //find query
	    // find all the todo and the the item passed as parameter
		Criteria criteriaV1 = Criteria.where("id").is(toDoId);
		Criteria criteriaV2 = Criteria.where("groceryToDoItems.groceryItemId").is(itemId);
		
	    Query findQuery = 
	    Query.query(new Criteria().andOperator(criteriaV1,criteriaV2));      
	 
	    //this will delete the todo item from the todo
	    //Update update = new Update().pull("groceryToDoItems", new BasicDBObject("groceryItemId",itemId) );

	    //this will update only the selected field to false.
	    Update update = new Update().set("groceryToDoItems.$.selected", false);
	    
	    // execute the update
	    WriteResult wr = mongoOperations.updateFirst(findQuery, update, GroceryToDo.class);
	    System.out.println(wr.toString());
	    

	}

}
