'use strict';

/* Services */

var listyServices = angular.module('listyServices', ['ngResource']);

var GROCERY_CATEGORY	= 	"/api/GroceryCategory/:categoryId";
//var GROCERY_ITEMS		= 	"/api/GroceryItem";
var GROCERY_ITEM		= 	"/api/GroceryItem/:itemId";

//var GROCERY_CARTS		= 	"/api/GroceryCart";
var GROCERY_CART		= 	"/api/GroceryCart/:cartId";
//var GROCERY_CART_ITEMS	= 	"/api/GroceryCart/:cartId/GroceryCartItem";
var GROCERY_CART_ITEM	= 	"/api/GroceryCart/:cartId/GroceryCartItem/:itemId";



var GROCERY_TODO		= 	"/api/GroceryToDo/:todoId";
var GROCERY_TODO_ITEM	= 	"/api/GroceryToDo/:todoId/GroceryToDoItem/:itemId";

	//************ Grocery Category
	listyServices.factory('GroceryCategory', ['$resource',function($resource){
	
		 return $resource(GROCERY_CATEGORY, {}, {
		        query: { method: 'GET', isArray: true },
		        update: { method: 'PUT', params: {categoryId: '@categoryId'} },
		        del: { method: 'DELETE', params: {categoryId: '@categoryId'} },
		        create: { method: 'POST' }
		    })
	  }
	]);

	//************ Grocery Item
	listyServices.factory('GroceryItem', ['$resource',function($resource){

		 return $resource(GROCERY_ITEM, {}, {
		        query: { method: 'GET', isArray: true },
		        update: { method: 'PUT', params: {itemId: '@itemId'} },
		        del: { method: 'DELETE', params: {itemId: '@itemId'} },
		        create: { method: 'POST' }
		    })
	  }
	]);

	//************ Grocery Cart
	listyServices.factory('GroceryCart', function ($resource) {
		
	    return $resource(GROCERY_CART, {}, {
	    	query: { method: 'GET', isArray: true },
	        update: { method: 'PUT', params: {cartId: '@cartId'} },
	        del: { method: 'DELETE', params: {cartId: '@cartId'} }
	    })
	});
	
	//************ Grocery Cart Item : Add or Del to/from Cart Id : id 
	listyServices.factory('GroceryCart_Item', function ($resource) {
		
	    return $resource(GROCERY_CART_ITEM,{}, {
		  create: {method:'PUT', params:{cartId: '@cartId'}},
	    	del: { method: 'DELETE', params: {cartId: '@cartId',itemId: '@itemId'} }
		   });
	});
	
	//************ Grocery ToDo
	listyServices.factory('GroceryToDo', function ($resource) {
		
	    return $resource(GROCERY_TODO, {}, {
	    	query: { method: 'GET' , isArray: true },
	        update: { method: 'PUT', params: {todoId: '@todoId'} },
	        del: { method: 'DELETE', params: {todoId: '@todoId'} }
	    })
	});
	//************ Grocery ToDo Item : Add or Del to/from ToDo Id : id 
	listyServices.factory('GroceryToDo_Item', function ($resource) {
		
	    return $resource(GROCERY_TODO_ITEM,{}, {
		    create: {method:'PUT', params:{todoId: '@todoId'}},
	    	del: { method: 'DELETE', params: {todoId: '@todoId',itemId: '@itemId'} }
		   });
	});
	
	
	
	
	
	
	//************ Grocery Cart Item  
//	listyServices.factory('GroceryCartItem', function ($resource) {
//		
//	    return $resource(API_GroceryCartItem_URL+'/:id',{}, {
//	    	del: { method: 'DELETE', params: {id: '@id'} }
//		   });
//	});
	
	//************ Grocery Special - get Items In Cart
//	listyServices.factory('GrocerySpecial_ItemsInCart', function ($resource) {
//		
//	    return $resource(API_GrocerySpecial_ItemsInCart_URL+'/:id', {}, {
//	        query: { method: 'GET',params: {id: '@id'} , isArray: true },	    	
//	    })
//	});		
	//************ Grocery Special - Add Item In Cart
//	listyServices.factory('GrocerySpecial_ItemCart', function ($resource) {
//		
//	    return $resource(GrocerySpecial_ItemCart_URL, {}, {
//	    	create: { method: 'POST',params: {id: '@id'} },
//	    })
//	});		
	