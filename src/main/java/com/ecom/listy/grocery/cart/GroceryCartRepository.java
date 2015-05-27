package com.ecom.listy.grocery.cart;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.listy.grocery.item.GroceryItem;

//@RepositoryRestResource(collectionResourceRel = "GroceryCart", path = "GroceryCart")
public interface GroceryCartRepository extends MongoRepository<GroceryCart, String>, GroceryCartRepositoryCustom {
	
	List<GroceryItem> findAllGroceryCartItemsGroceryItemById(String id);
	


}
