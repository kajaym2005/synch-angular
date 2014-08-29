(function () {
'use strict';

/*************************************/
angular.module('cargoopsApp').controller('UserListController', ['$scope','$location','aswSession','aswCalendar','UserService',function UserListController($scope,$location,aswSession,aswCalendar,UserService) {
		
	$scope.editUser = function (userId) {
	  $location.path('/useredit/' + userId);
	};

	$scope.deleteUser = function (userId) {
		UserService.deleteUser({id:userId},function(user,responseHeaders){
			$scope.users = UserService.getUsers();
		});
	};

	$scope.createNewUser = function () {
		$location.path('/usercreate');
	};	

	var defaultFilter =  {value:'',sortBy:'firstName',sortAsc:true};		
	aswSession.storeProperty('filter','storedFilter',defaultFilter);

	var filter = $scope.filter;

	var userList = $scope.userList = UserService.getUsers(filterAndSortResults);

	$scope.$watch('filter', filterAndSortResults, true);

	function filterAndSortResults() {
		$scope.users = [];
		// filter
		angular.forEach(userList, function(item, key) {
			if($scope.filter.value !== ''){
				var itemValue = JSON.stringify(item);
				if(itemValue.indexOf($scope.filter.value) === -1){
					 return;
				}
			}
			$scope.users.push(item);
		});

		// sort
		$scope.users.sort(function(a, b) {
			if (a[filter.sortBy] > b[filter.sortBy]) {
				return filter.sortAsc ? 1 : -1;
			}
			else if (a[filter.sortBy] < b[filter.sortBy]) {
				return filter.sortAsc ? -1 : 1;
			}
			return 0;
		});
	}

	$scope.sortBy = function(key) {
		if (filter.sortBy === key) {
		  filter.sortAsc = !filter.sortAsc;
		} 
		else {
		  filter.sortBy = key;
		  filter.sortAsc = true;
		}
	};

	$scope.sortIconFor = function(key) {
		if (filter.sortBy !== key) {
		  return '';
		}
		return filter.sortAsc ? '\u25B2' : '\u25BC';
	};

	$scope.calendar = aswCalendar.config();   	
}]);

/*************************************/
angular.module('cargoopsApp').controller('UserEditController',['$scope', '$location','$routeParams','aswCalendar','UserService',function UserEditController($scope, $location,$routeParams,aswCalendar,UserService) { 

	$scope.updateUser = function () {
		if($scope.userForm.$valid){
			UserService.updateUser($scope.user,function (user,responseHeaders){
				$location.path('/userlist');
			});
		}
	};
	
	$scope.createUser = function () {
		if($scope.userForm.$valid){
			UserService.createUser($scope.user,function (user,responseHeaders){
				$location.path('/userlist');
			});
		}
	};
	$scope.cancel = function () {
	  $location.path('/userlist');
	};

	$scope.isEditMode = ($routeParams.id !== undefined);    

	if($scope.isEditMode){
		$scope.user = UserService.getUser({id: $routeParams.id});
	}

	$scope.calendar = aswCalendar.config(); 
}]);
/*************************************/



})(); // ends main function