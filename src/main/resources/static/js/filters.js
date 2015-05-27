/*
 * This filter is used to check if a given item id exist in the provided cartItems list, the result should be 
 * one item only if the item exist in the cart items, else the list result after the fitler will be null.
 */

angular.module('listyFilters', []).filter('checkItemIdInItems', function() {
	
	
	return function( collItems, id) {
	    var filtered = [];
	    angular.forEach(collItems, function(collItem) {
	      if(id == collItem.groceryItemId) {
	        filtered.push(collItem);
	      }
	    });
	    return filtered;
	  };
	  
	  
});