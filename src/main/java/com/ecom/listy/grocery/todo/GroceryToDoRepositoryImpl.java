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

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class GroceryToDoRepositoryImpl implements GroceryToDoRepositoryCustom {

//	@Autowired MongoTemplate mongoTemplate;
	@Autowired 
	MongoOperations mongoOperations;
	@Autowired 
	MongoTemplate mongoTemplate;	
	
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
		Criteria criteriaV2 = Criteria.where("groceryToDoItems.selected").is(Boolean.TRUE);
		
	    Query findQuery = 
	    Query.query(new Criteria().andOperator(criteriaV1,criteriaV2));      
	 
	    List<GroceryToDo> todo = mongoOperations.find(findQuery,GroceryToDo.class);
	    System.out.println(todo);
	    //this will delete the todo item from the todo
	    //Update update = new Update().pull("groceryToDoItems", new BasicDBObject("groceryItemId",itemId) );

	    //this will update only the selected field to false.
	    //Update update = new Update().set("groceryToDoItems.$.selected", false);
	    
	    // execute the update
	    //GroceryToDo temp = mongoOperations.findAndModify(findQuery, update, GroceryToDo.class);

	    //WriteResult wr = mongoOperations.updateMulti(findQuery, Update.update("groceryToDoItems.$.selected", false), GroceryToDo.class);
	    //System.out.println(wr.toString());
/*	    if (temp == null){
		    //groceryToDoItem.setSelected(true);
	    	System.out.println("not found to clear!!");
	    } else {
	    	System.out.println("items cleared");
	    }*/
	    
/*	    DBObject query = new BasicDBObject("_id", toDoId)
	    .append("groceryToDoItems.selected", true);
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("groceryToDoItems.selected", false));
		mongoOperations.getCollection("GroceryToDo").findAndModify(query, update);*/
	    
/*		BasicDBObject searchQuery = new BasicDBObject();  
		searchQuery.put("id", new ObjectId(toDoId));  
		searchQuery.put("groceryToDoItems.selected", true);  
		   
		DBCursor cursor = mongoOperations.getCollection("GroceryToDo").find(searchQuery);  
		               
		while(cursor.hasNext())  
		{  
		   System.out.println(cursor.next()); 
		   String firstName = searchQuery.getString("_id");  
		   String lastName = searchQuery.getString("groceryToDoItems.selected");
		}*/
		
		
/*        BasicDBObject match = new BasicDBObject();
        match.put( "_id",  new ObjectId(toDoId) );
        match.put( "groceryToDoItems.selected",true);

        BasicDBObject addressSpec = new BasicDBObject();

            addressSpec.put( "groceryToDoItems.$.selected", false);


        BasicDBObject update = new BasicDBObject();
        update.put( "$set", addressSpec );

        mongoOperations.getCollection("groceryToDo").update( match, update );*/
	    
        
        
        
        DBCursor cursor;
        DBCollection table = mongoOperations.getCollection("groceryToDo");
        
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put( "_id",  new ObjectId(toDoId) );
		searchQuery.put( "groceryToDoItems.selected",true);
		
		cursor = table.find(searchQuery);
		
		while(cursor.hasNext())  
		{
			System.out.println(cursor.next()); 
			System.out.println(cursor.size());
			
	        BasicDBObject addressSpec = new BasicDBObject();

            addressSpec.put( "groceryToDoItems.$.selected", false);


	        BasicDBObject update = new BasicDBObject();
	        update.put( "$set", addressSpec );
	
	        mongoOperations.getCollection("groceryToDo").update( searchQuery, update );
        
	        cursor = table.find(searchQuery);
		}


        
/*        BasicDBList grades = new BasicDBList();
        grades.add(new BasicDBObject("grade", 80).append("mean", 75).append("std", 8));
        grades.add(new BasicDBObject("grade", 85).append("mean", 90).append("std", 5));
        grades.add(new BasicDBObject("grade", 90).append("mean", 85).append("std", 3));
 
        collection.save(new BasicDBObject("_id", 4).append("grades", grades));*/
 
/*	    System.out.println(mongoOperations.getCollection("groceryToDo").getCount());
        
	    mongoOperations.getCollection("groceryToDo").update( new BasicDBObject("groceryToDoItems.selected",true),
                           new BasicDBObject("$set", new BasicDBObject("groceryToDoItems.$.selected", false)) );
 
        System.out.println(mongoOperations.getCollection("groceryToDo").find(new BasicDBObject("groceryToDoItems.selected",true)));
        */
        



	}

}
