package com.ecom.listy.grocery.todo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.listy.grocery.item.GroceryItem;

public interface GroceryToDoRepository extends MongoRepository<GroceryToDo, String>, GroceryToDoRepositoryCustom {
	
	List<GroceryItem> findAllGroceryToDoItemsGroceryItemById(String id);
	


}
