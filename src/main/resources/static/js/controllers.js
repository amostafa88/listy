
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
listyControllers.controller('navCtrl', ['$scope', 'localize',
	function($scope, localize) {
	
	localize.setLanguage('ar-SA');
	
    $scope.setEnglishLanguage = function() {
        localize.setLanguage('en-US');
    };

    $scope.setArabicLanguage = function() {
        localize.setLanguage('ar-SA');
    };

}]);


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
				todoItem.groceryItemId = item.id;
				console.log("todo.id="+todo.id)
				todoItem.$create(
						{todoId:todo.id}, //arguments 
						function(response){
							//on success call back
							$scope.currentGroceryToDo.groceryToDoItems.push(todoItem);
							console.log("Item added : "+JSON.stringify(response.groceryItemId));

						}, function(response) {
							//on failure call back
							console.log("Faild to add item, error json : "+ JSON.stringify(response.data));
						});
			};
			//##############  Delete Cart Item. 
			$scope.deleteToDoItem = function(todo,item) {

				console.log("Delete cart item id :"+item.groceryItemId)

				GroceryToDo_Item.del(
						{ todoId: todo.id, itemId: item.groceryItemId },
						function(response){
							//on success call back
							todo.groceryToDoItems.splice(todo.groceryToDoItems.indexOf(item), 1);
							console.log('Item removed.');
						}, function(response) {
							//on failure call back
							console.log("Faild to delete item, error json : "+ JSON.stringify(response.data));
						});
			};

			//############## Toggle ToDo Item Selection, Will add / Remove Cart Items from a Cart 
			$scope.toggleToDoItemSelection = function(todo,item) {

				console.log("Checking todo items for id = "+item.id);
				console.log("todo Item size="+todo.length);
				console.log("todo Item id="+todo.id);
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

				if (todoItem[0] !=null) {//exist, so we delete it:
					console.log('Item exists in the todo ... removing it.');
					$scope.deleteToDoItem(todo, todoItem[0]);
					//console.log("Item removed from the cart items list.");

				}
				
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