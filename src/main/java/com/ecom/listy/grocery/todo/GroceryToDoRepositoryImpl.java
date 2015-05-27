package com.ecom.listy.grocery.todo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class GroceryToDoRepositoryImpl implements GroceryToDoRepositoryCustom {

//	@Autowired MongoTemplate mongoTemplate;
//	@Autowired MongoOperations mongoOperations;
	@Autowired
	private GroceryToDoRepository groceryToDoRepository;

	
	@Override
	public GroceryToDoItem saveToToDoId(String id,GroceryToDoItem groceryToDoItem){
		
		//get the Cart
		GroceryToDo toDo = groceryToDoRepository.findOne(id);
		List<GroceryToDoItem> toDoItemsList = toDo.getGroceryToDoItems();
		//groceryCartItemRepository.save(groceryCartItem);
		toDoItemsList.add(groceryToDoItem);
		groceryToDoRepository.save(toDo);
		return groceryToDoItem;//with the id
		
	}
	
	@Override
	public void deleteItemFromToDo(String toDoId,String itemId){
		//get the Cart
		System.out.println("calling .. delete");
		GroceryToDo toDo = groceryToDoRepository.findOne(toDoId);
		List<GroceryToDoItem> toDoItemsList = toDo.getGroceryToDoItems();
		toDoItemsList.remove(new GroceryToDoItem(0,"",itemId));
		
		toDo.setGroceryToDoItems(toDoItemsList);
		groceryToDoRepository.save(toDo);
	}

}
