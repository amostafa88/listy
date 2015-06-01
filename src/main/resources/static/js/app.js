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