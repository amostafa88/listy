package com.ecom.listy.grocery.item;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "GroceryItem", path = "GroceryItem")
public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {

	@RestResource(rel = "byName", path = "byName")
	Page<GroceryItem> findByNameEn(@Param("name")String name,Pageable p);

//	@Query("{ 'groceryCategories': "
//			+ "{'$ref': 'groceryCategory'"
//			+ ", '$name': {'$en': { '$en' : ?0 }} "
//			+ "} "
//			+ "}")
//	@Query("{ 'description' : ?0 }")
//	@Query("{ 'name.en' : ?0 }")
	@Query("{ 'groceryCategories.id' : ?0 }")
	List<GroceryItem> findByCatId(String id);

	
}
