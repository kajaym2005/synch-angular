(function () {
'use strict';

angular.module('cargoopsApp').factory('UserService', ['$resource','appConfig',function($resource,appConfig) {
 	 return $resource(appConfig.USER_API_URL_ID, {}, {
        getUsers	: { method: 'GET', isArray: true,url:appConfig.USER_API_URL},
        createUser	: { method: 'POST',url:appConfig.USER_API_URL },
        getUser		: { method: 'GET'},
		updateUser	: { method: 'PUT', params: {id: '@id'} },
        deleteUser	: { method: 'DELETE', params: {id: '@id'} }
    });
}]);


})(); // ends main function