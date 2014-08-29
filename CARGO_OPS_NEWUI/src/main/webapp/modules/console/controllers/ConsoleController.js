(function () {
'use strict';

angular.module('cargoopsApp').controller('NavbarController',['$scope','$location',function NavbarController($scope, $location) {
	  $scope.routeIs = function(routeName) {
		return $location.path() === routeName;
	  };
}]);

angular.module('cargoopsApp').controller('ConsoleController',['$scope',function ConsoleController($scope) {
    

}]);

})(); // ends main function