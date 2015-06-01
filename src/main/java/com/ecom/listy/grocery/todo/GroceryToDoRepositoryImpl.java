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
		
 	    //this will update only the "selected" field to true as the default for any newly added fields.
	    Update update = new Update().set("groceryToDoItems.$.selected", true);
	    
    	findQuery = new Query(Criteria.where("id").is(toDoId));
    	groceryToDoItem.setSelected(true);
	    update = new Update().addToSet("groceryToDoItems",groceryToDoItem);
	    mongoOperations.findAndModify(findQuery, update, GroceryToDo.class);
	    
	    return groceryToDoItem;//with the id
		
	}
	
	
	@Override
	public void updateItem(String toDoId,GroceryToDoItem groceryToDoItem){
	
		Criteria criteriaV1 = Criteria.where("id").is(toDoId);
		Criteria criteriaV2 = Criteria.where("groceryToDoItems.groceryItemId").is(groceryToDoItem.getGroceryItemId());
		
	    Query findQuery = 
	    Query.query(new Criteria().andOperator(criteriaV1,criteriaV2));
		
 	    //this will update only the selected field to false.
	    System.out.println("The item new selected status received as : "+ groceryToDoItem.isSelected());
	    
	    //if the item is selected i will update it 
	    if (groceryToDoItem.isSelected() == true){

	    	Update update = new Update().set("groceryToDoItems.$.selected", groceryToDoItem.isSelected());
		    
		    //first lookup the item on the above criteria if found will be updated, if not found do nothing. (item not found case is weird and should not happen).
		    GroceryToDo temp = mongoOperations.findAndModify(findQuery, update, GroceryToDo.class);
		    //WriteResult wr = mongoOperations.updateFirst(findQuery, update, GroceryToDo.class);
		    
		    //null means that the item not found so we will insert it:
		    if (temp == null){
			    //groceryToDoItem.setSelected(true);
		    	System.out.println("not found to update!!");
		    } else {
		    	System.out.println("item found");
		    }
	    
		    
	    } else {
	    	//if the item is unselected, i need to check if the item has valuable information, to keep , 
	    	//else no point to keep it, i will use the passed object content to check if it is valuable or not.
    	
	    	if (!doesItContainValuableInfo(groceryToDoItem)){
		    	
		    	Update update = new Update().pull("groceryToDoItems", new BasicDBObject("groceryItemId",groceryToDoItem.getGroceryItemId()) );
		    	
		    	WriteResult wr = mongoOperations.updateFirst(findQuery, update, GroceryToDo.class);
		    	
		    	System.out.println(wr.toString());
	    	}
	    }
	    
	    //return groceryToDoItem;//with the id
		
	}
	
	
	boolean doesItContainValuableInfo(GroceryToDoItem groceryToDoItem){
		System.out.println(groceryToDoItem.getQuantity());
		System.out.println(groceryToDoItem.getComments());
		if (groceryToDoItem.getQuantity() != 0 &&
				groceryToDoItem.getComments() != null && !groceryToDoItem.getComments().trim().equals(""))
			return true; //contains valuable infomraiton we need to keep.
		else 
			return false;
	}
	
	
	@Override
	public void clearSelectedItems(String toDoId){
		
		//get the Cart
/*		System.out.println("calling .. delete");
		GroceryToDo toDo = groceryToDoRepository.findOne(toDoId);
		List<GroceryToDoItem> toDoItemsList = toDo.getGroceryToDoItems();
		toDoItemsList.remove(new GroceryToDoItem(false,0,"",itemId,""));
		
		toDo.setGroceryToDoItems(toDoItemsList);
		groceryToDoRepository.save(toDo);*/
		
		///
		
	    //find query
	    // find all the todo with id and its item is selected.
		Criteria criteriaV1 = Criteria.where("id").is(toDoId);
		Criteria criteriaV2 = Criteria.where("groceryToDoItems.selected").is(true);
		
	    Query findQuery = 
	    Query.query(new Criteria().andOperator(criteriaV1,criteriaV2));      
	 
	    //this will delete the todo item from the todo
	    //Update update = new Update().pull("groceryToDoItems", new BasicDBObject("groceryItemId",itemId) );

	    //this will update only the selected field to false.
	    Update update = new Update().set("groceryToDoItems.$.selected", false);
	    
	    // execute the update
	    GroceryToDo temp = mongoOperations.findAndModify(findQuery, update, GroceryToDo.class);

	    //WriteResult wr = mongoOperations.updateFirst(findQuery, update, GroceryToDo.class);
	    //System.out.println(wr.toString());
	    if (temp == null){
		    //groceryToDoItem.setSelected(true);
	    	System.out.println("not found to clear!!");
	    } else {
	    	System.out.println("items cleared");
	    }

	}

}
