package com.ecom.listy.grocery.category;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "GroceryCategory", path = "GroceryCategory")
public interface GroceryCategoryRepository extends MongoRepository<GroceryCategory, String> {

	GroceryCategory findByNameEn(@Param("name")String name);
	
}
