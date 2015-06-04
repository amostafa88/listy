
var listyControllers = angular.module('listyControllers', []);

//ui controllers
/* Collabse */
listyControllers.controller('CollapseDemoCtrl', function ($scope) {
	  $scope.isCollapsed = false;
	});

listyControllers.controller('AccordionDemoCtrl', function ($scope) {
	
    $scope.oneAtATime = true;
    

    $scope.groups = [
      {
        title: 'Vegetables',
        content: 'Vegetables Content'
      },
      {
        title: 'Fruits',
        content: 'Fruits Content'
      }
    ];

    $scope.items = ['Item 1', 'Item 2', 'Item 3'];

    $scope.addItem = function() {
      var newItemNo = $scope.items.length + 1;
      $scope.items.push('Item ' + newItemNo);
    };

    $scope.status = {
      isFirstOpen: true,
      isFirstDisabled: false
    };
    
    
    /////list
     $scope.listitems = [];

    // Initialize model
    var id = 10;

            for (var i = 0; i < 7; ++i) {
                $scope.listitems.push({label: 'Item ' + i});
            }
 

    $scope.$watch('listitems', function(listitems) {
        $scope.listitemsAsJson = angular.toJson(listitems, true);
    }, true);


  });



/**************************************
 * UI Handling Controllers
 **************************************/
listyControllers.controller('navCtrl', ['$scope', 'localize','Special_Reload',
	function($scope, localize,Special_Reload) {
	
	localize.setLanguage('ar-SA');
	
    $scope.setEnglishLanguage = function() {
        localize.setLanguage('en-US');
    };

    $scope.setArabicLanguage = function() {
        localize.setLanguage('ar-SA');
    };
    
	//++++++++++++++++++=
	$scope.reloadDb = function() {
		Special_Reload.get();
	}

}]);




listyControllers.controller('ModalDemoCtrl', function ($scope, $modal, $log) {

	  $scope.items = ['item1', 'item2', 'item3'];

	  $scope.animationsEnabled = true;

	  //open confirmation model:
	  $scope.openConfirmationModel= function (size, message,groceryToDoItems) {

		  	$scope.message = message;
		  	
		  	/*$scope.todoId = todoId;*/
		  	
		  	/*console.log(' > todo id = '+todoId);*/
		  	
		    var modalInstance = $modal.open({
		      animation: $scope.animationsEnabled,
		      templateUrl: 'confimration.html',
		      controller: 'ModalInstanceCtrl',
		      size: size,
		      resolve: {
		    	  message: function () {
		          return message;
		        }/*,
		          todoId: 	function () {
			          return todoId;
			     }*/,
			     currentGroceryToDo: function () {
			          return $scope.$parent.currentGroceryToDo;
			     }
		    
		      }
		    });

		    modalInstance.result.then(function (selectedItem) {
		      $scope.selected = selectedItem;
		    }, function () {
		      $log.info('Modal dismissed at: ' + new Date());
		    });
		  };

	  //general open  
	  $scope.open = function (size) {

	    var modalInstance = $modal.open({
	      animation: $scope.animationsEnabled,
	      templateUrl: 'confimration.html',
	      controller: 'ModalInstanceCtrl',
	      size: size,
	      resolve: {
	        items: function () {
	          return $scope.items;
	        }
	      }
	    });

	    modalInstance.result.then(function (selectedItem) {
	      $scope.selected = selectedItem;
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    });
	  };

	  $scope.toggleAnimation = function () {
	    $scope.animationsEnabled = !$scope.animationsEnabled;
	  };

	});

//Please note that $modalInstance represents a modal window (instance) dependency.
//It is not the same as the $modal service used above.

listyControllers.controller('ModalInstanceCtrl', function ($scope,$filter, $modalInstance,GroceryToDo, message,currentGroceryToDo) {

	
	$scope.clearItems = function(){
		console.log('>> todo id = '+currentGroceryToDo.id);
		
		GroceryToDo.clear(
				{ todoId: currentGroceryToDo.id},
				function(response){
					console.log('Cleared in back end.');
			       
					console.log(currentGroceryToDo.groceryToDoItems.length);
					
					//get the selected item in a new list
					trueitems = ($filter('filter')(currentGroceryToDo.groceryToDoItems, {selected : true}) );
					
					console.log(trueitems.length);
					
				       for (var i=0; i< trueitems.length; i++){
				    	    item = trueitems[i]
				    	    currentGroceryToDo.groceryToDoItems.splice(currentGroceryToDo.groceryToDoItems.indexOf(item), 1);
							//then update its selected to false
							item.selected = false;
							//and then push it back
							currentGroceryToDo.groceryToDoItems.push(item);
				        }
				       
				       console.log('Cleared in front end.');
					
				}, function(response) {
					console.log("Faild to clear items, error json : "+ JSON.stringify(response.data));
				});
	}
	
	$scope.message = message;

	$scope.ok = function () {
		$modalInstance.close($scope.clearItems());
	};
	
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});


/*angular.module('ui.bootstrap.demo').controller('ModalInstanceCtrl', function ($scope, $modalInstance, items) {

	  $scope.items = items;
	  $scope.selected = {
	    item: $scope.items[0]
	  };

	  $scope.ok = function () {
	    $modalInstance.close($scope.selected.item);
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	});*/


listyControllers.

controller('ctrl', function ($scope) {
    $scope.scrollTo = function (target){
    };
});
/**************************************
 * Back End Handling Controllers
 **************************************
 **/
//Handle Listing
listyControllers.controller('ToDoListCtrl', 
		['$scope','$filter','$resource','GroceryCategory', 'GroceryItem','GroceryToDo','GroceryToDo_Item',
		 function ($scope,$filter,$resource,GroceryCategory, GroceryItem,GroceryToDo,GroceryToDo_Item) {


			//##############  init:
			
		    $scope.categories = GroceryCategory.query();
		    
			//get all items
			$scope.items = GroceryItem.query();

			//get grocery todos and set the current grocery cart 
			$scope.groceryToDos =  GroceryToDo.query(
					{},
					function(response){
						//on success call back
						
						$scope.currentGroceryToDo = response[0]; // <<< will use cart index 0 for out dev >>>
						console.log(JSON.stringify(response[0]));
						
					});
			

			
			//##############  Delete Item from Items list
			$scope.deleteItem = function(item) {

				GroceryItemH.del({ id: item.id });
				$scope.items.splice($scope.items.indexOf(item), 1);
			};


			//##############  Add ToDo item to a ToDo
			$scope.addItemToToDo = function(todo,item) {

				var todoItem = new GroceryToDo_Item();
				//cartItem.quantity = 1;
				//cartItem.comments = "please work";
				todoItem.selected = true;
				todoItem.groceryItemId = item.id;
				console.log("cat : "+item.groceryCategories[0].id)
				todoItem.groceryCategoryId = item.groceryCategories[0].id;
				console.log("todo.id="+todo.id)
				todoItem.$create(
						{todoId:todo.id}, //arguments 
						function(response){
							//on success call back
							todoItem.selected = true;
							$scope.currentGroceryToDo.groceryToDoItems.push(todoItem);
							console.log("Item added : "+JSON.stringify(response.groceryItemId));

						}, function(response) {
							//on failure call back
							console.log("Faild to add item, error json : "+ JSON.stringify(response.data));
						});
			};
			//##############  Revese ToDo item Selection
			$scope.reverseToDoItemSelection = function(todo,item) {

				var todoItem = new GroceryToDo_Item();
				//cartItem.quantity = 1;
				//cartItem.comments = "please work";
				todoItem.groceryItemId = item.groceryItemId;
				todoItem.groceryCategoryId = item.groceryCategoryId;
				todoItem.comments = item.comments;
				console.log("todo item id :"+todoItem.groceryItemId);
				console.log("todo selected before inverse :"+item.selected);
				todoItem.selected = !item.selected;
				console.log("todo selected after inverse :"+todoItem.selected);
				//console.log("todo.id="+todo.id)
				todoItem.$update(
						{ todoId: todo.id }, //arguments 
						function(response){
							//on success call back
							// to do the update we first remove the item 
							todo.groceryToDoItems.splice(todo.groceryToDoItems.indexOf(item), 1);
							//then update its selected 
							item.selected = !item.selected;
							//and then push it back
							$scope.currentGroceryToDo.groceryToDoItems.push(item);
							console.log("Item : "+JSON.stringify(response.groceryItemId) +" is now " + item.selected);

						}, function(response) {
							//on failure call back
							console.log("Faild to add item, error json : "+ JSON.stringify(response.data));
						});
			};
			//##############  Delete Cart Item. 
/*			$scope.deleteToDoItem = function(todo,item) {

				console.log("Delete cart item id :"+item.groceryItemId)

				GroceryToDo_Item.del(
						{ todoId: todo.id, itemId: item.groceryItemId },
						function(response){
							//on success call back
							// to do the update we first remove the item 
							todo.groceryToDoItems.splice(todo.groceryToDoItems.indexOf(item), 1);
							//then update its selected
							todoItem.selected = false;
							//and then push it back
							$scope.currentGroceryToDo.groceryToDoItems.push(todoItem);							
							console.log('Item unselected.');
						}, function(response) {
							//on failure call back
							console.log("Faild to delete item, error json : "+ JSON.stringify(response.data));
						});
			};*/

			//############## Toggle ToDo Item Selection, Will add / Remove Cart Items from a Cart 
			$scope.toggleToDoItemSelection = function(todo,item) {

				console.log("Checking todo items for id = "+item.id);
				/*console.log("todo Items size="+todo.length);*/
				console.log("todo id="+todo.id);
				var todoItem = $filter('checkItemIdInItems')(todo.groceryToDoItems, item.id);



				//printing all current cart items
				//for (i = 0; i < cart.groceryCartItems.length; i++) { 
				//	console.log(" Item "+i+" id = "+cart.groceryCartItems[i].groceryItemId);
				//}

				if (todoItem[0] == null){//the item doesn't exist before, so we add it.
					console.log("Item doesn't exist in todo ... adding it.");
					$scope.addItemToToDo(todo,item);
					//console.log("Item added to the todo items list.");
				} 
				
				if (todoItem[0] != null){//the item exist, so i will reverse its selected in the front end and in the back end.
					console.log("Item exists in todo, and it is "+ todoItem[0].selected +"... will reverse that.");
					$scope.reverseToDoItemSelection(todo,todoItem[0]);
					//console.log("Item added to the todo items list.");
				} 				
				
/*				if (todoItem[0] !=null && todoItem[0].selected == true) {//exist and was selected, so we delete it:
					console.log('Item exists in the todo ... removing it.');
					$scope.deleteToDoItem(todo, todoItem[0]);
					//console.log("Item removed from the cart items list.");
				}*/
				
				
				//printing all current cart item
				//console.log("cart Items size="+cart.groceryCartItems.length);
				//for (i = 0; i < cart.groceryCartItems.length; i++) { 
				//	console.log(" Item "+i+" id = "+cart.groceryCartItems[i].groceryItemId);
				//}

			}
			


		}]);






//Handle Cart Listing
listyControllers.controller('CartListCtrl', 
		['$scope','$filter','$resource', 'GroceryItem','GroceryCart','GroceryCart_Item',
		 function ($scope,$filter,$resource, GroceryItem, GroceryCart,GroceryCart_Item) {

			//##############  init:
			//get all items
			$scope.items = GroceryItem.query();

			//get grocery carts and set the current grocery cart 
			$scope.groceryCarts =  GroceryCart.query(
					{},
					function(response){
						//on success call back
						$scope.currentGroceryCart = response[0]; // <<< will use cart index 0 for out dev >>>
						console.log(JSON.stringify(response[0]));
					});


			//##############  Delete Item from Items list
			$scope.deleteItem = function(item) {

				GroceryItemH.del({ id: item.id });
				$scope.items.splice($scope.items.indexOf(item), 1);
			};

			//##############  Add Cart item to a Cart
			$scope.addItemToCart = function(cart,item) {

				var cartItem = new GroceryCart_Item();
				//cartItem.quantity = 1;
				//cartItem.comments = "please work";
				cartItem.groceryItemId = item.id;

				cartItem.$create(
						{cartId:cart.id}, //arguments 
						function(response){
							//on success call back
							$scope.currentGroceryCart.groceryCartItems.push(cartItem);
							console.log("Item added : "+JSON.stringify(response.groceryItemId));

						}, function(response) {
							//on failure call back
							console.log("Faild to add item, error json : "+ JSON.stringify(response.data));
						});
			};
			//##############  Delete Cart Item. 
			$scope.deleteCartItem = function(cart,item) {

				console.log("Delete cart item id :"+item.groceryItemId)

				GroceryCart_Item.del(
						{ cartId: cart.id, itemId: item.groceryItemId },
						function(response){
							//on success call back
							cart.groceryCartItems.splice(cart.groceryCartItems.indexOf(item), 1);
							console.log('Item removed.');
						}, function(response) {
							//on failure call back
							console.log("Faild to delete item, error json : "+ JSON.stringify(response.data));
						});
			};

			//############## Toggle Cart Item Selection, Will add / Remove Cart Items from a Cart 
			$scope.toggleCartItemSelection = function(cart,item) {

				console.log("Checking cart items for id = "+item.id);
				var cartItem = $filter('checkItemIdInItems')(cart.groceryCartItems, item.id);

				//console.log("cartItem size="+cartItem.length);
				//console.log("cart Items size="+cart.groceryCartItems.length);

				//printing all current cart items
				//for (i = 0; i < cart.groceryCartItems.length; i++) { 
				//	console.log(" Item "+i+" id = "+cart.groceryCartItems[i].groceryItemId);
				//}

				if (cartItem[0] == null){//the item doesn't exist before, so we add it.
					console.log("Item doesn't exist in cart ... adding it.");
					$scope.addItemToCart(cart,item);
					console.log("Item added to the cart items list.");
				} 

				if (cartItem[0] !=null) {//exist, so we delete it:
					console.log('Item exists in the cart ... removing it.');
					$scope.deleteCartItem(cart, cartItem[0]);
					//console.log("Item removed from the cart items list.");

				}
				
				//printing all current cart item
				//console.log("cart Items size="+cart.groceryCartItems.length);
				//for (i = 0; i < cart.groceryCartItems.length; i++) { 
				//	console.log(" Item "+i+" id = "+cart.groceryCartItems[i].groceryItemId);
				//}

			}
			

		}]);

//	phonecatControllers.controller('DetailCtrl', ['$scope', '$routeParams', '$http',
//	                                                   function($scope, $routeParams, $http) {
//		
//		$http.get('phones/' + $routeParams.phoneId + '.json').success(function(data) {
//			$scope.phone = data;
//		});
//	
//	}]);
	
	
	
//	helloApp.controller("HttpController", [ '$scope', '$resource',
//	                            			function($scope, $resource) {
//			$scope.users = [
//	                    { 'firstname':'Ajitesh',
//	                    	'lastname':'Shukla',
//	                    	'address':'Hyderbad',
//	                    	'email':'ajitesh101@gmail.com'},
//	                    	{ 'firstname':'Sumit',
//		                    	'lastname':'Jha',
//		                    	'address':'Muzaffarpur',
//		                    	'email':'sumitjha2011@yahoo.com'},			                    				                    	
//	                    ];
//			
//			$scope.saveUser = function(){
//				$scope.users.push({ 'firstname':$scope.firstname, 'lastname': $scope.lastname, 'address':$scope.address, 'email':$scope.email });		
//				// Create a resource class object
//				//
//				var User = $resource('/user/new');
//				// Call action method (save) on the class 
//				//
//				User.save({firstname:$scope.firstname,lastname:$scope.lastname,address:$scope.address,email:$scope.email}, function(response){
//					$scope.message = response.message;
//				});
//				
//				$scope.firstname='';
//				$scope.lastname='';
//				$scope.address='';
//				$scope.email='';
//			}
//			
//			$scope.updateUser = function(){							
//				// Create a resource class object
//				//
//				var User = $resource('/user/:userId', {userId:'@id'});
//				// Create an instance of User resource
//				var user = User.get({userId:25});
//				// Update the new values entered in the form fields
//				//
//				user.id = 25;
//				user.firstname = $scope.firstname;
//				user.lastname = $scope.lastname;
//				user.address = $scope.address;
//				user.email = $scope.email;
//				// Call '$' prefixed action menthod to post ("save" )
//				//
//				user.$save(function(response){
//					$scope.message = response.message;
//				});
//				// Push the new objects in the $scope.users 					
//				//
//				$scope.users.push({ 'firstname':$scope.firstname, 'lastname': $scope.lastname, 'address':$scope.address, 'email':$scope.email });
//				$scope.firstname='';
//				$scope.lastname='';
//				$scope.address='';
//				$scope.email='';
//			}				
//		} ]);