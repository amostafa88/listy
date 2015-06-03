'use strict';

/* App Module */

var listyApp = angular.module('listyApp', [
  //'ngRoute',
  'localization',  //for intenationailziation
  'listyControllers',
  'listyServices',
  'listyFilters',
  'ui.bootstrap' //for angularui
]);


listyApp.directive('onErrorSrc', function() {
    return {
        link: function(scope, element, attrs) {
          element.bind('error', function() {
            if (attrs.src != attrs.onErrorSrc) {
              attrs.$set('src', attrs.onErrorSrc);
            }
          });
        }
    }
});




listyApp.directive("stickyNav", function stickyNav($window){  
	  function stickyNavLink(scope, element){
		    var w = angular.element($window),
		        size = element[0].clientHeight,
		        top = 0;

		    function toggleStickyNav(){
		      if(!element.hasClass('controls-fixed') && $window.pageYOffset > top + size){
		        element.addClass('controls-fixed');
		      } else if(element.hasClass('controls-fixed') && $window.pageYOffset <= top + size){
		       // element.removeClass('controls-fixed');
		      }
		    }

		    scope.$watch(function(){
		      return element[0].getBoundingClientRect().top + $window.pageYOffset;
		    }, function(newValue, oldValue){
		      if(newValue !== oldValue && !element.hasClass('controls-fixed')){
		        top = newValue;
		      }
		    });

		    w.bind('resize', function stickyNavResize(){
		      element.removeClass('controls-fixed');
		      top = element[0].getBoundingClientRect().top + $window.pageYOffset;
		      toggleStickyNav();
		    });
		    w.bind('scroll', toggleStickyNav);
		  }

		  return {
		    scope: {},
		    restrict: 'A',
		    link: stickyNavLink
		  };
		});