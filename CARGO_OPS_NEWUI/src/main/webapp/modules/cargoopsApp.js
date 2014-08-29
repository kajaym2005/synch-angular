(function () {
'use strict';

//Add angucomplete-alt is autocomplete plugin
var cargoopsApp = angular.module('cargoopsApp', ['ngRoute','ngResource','ui.bootstrap','angucomplete-alt']);

/*************************************/
//Application parameters

cargoopsApp.value('appConfig', appParams);

/*************************************/
//ui.bootstrap - global
cargoopsApp.config(function (datepickerConfig, datepickerPopupConfig) {
    datepickerConfig.showWeeks = false;
    datepickerPopupConfig.showButtonBar = false;
});

/*************************************/
//window.localStorage - stores data with no expiration date
//code.sessionStorage - stores data for one session (data is lost when the tab is closed)
cargoopsApp.value('localStorage', window.localStorage);
cargoopsApp.value('sessionStorage', window.sessionStorage);

/*************************************/
//LOG 
cargoopsApp.config(['$logProvider','aswLogProvider', function($logProvider,aswLogProvider) {
	 $logProvider.debugEnabled(true);
	 //DateTime,Context,log
     aswLogProvider.loggingPattern = '{0} : {1} > {2}';
     aswLogProvider.logLevel=1;   //1=debug,2=info,3=warn,4=error
}]);

cargoopsApp.run(['$log', 'aswLog', function ($log, aswLog) {
    aswLog.enhanceAngularLog($log);
}]);


/*************************************/

cargoopsApp.config(['$httpProvider',function ($httpProvider) {
	/*
		Here we can add application specific header if needed
		//$httpProvider.defaults.headers.common['X-Requested-With'] = true;
		//$http.defaults.headers.get.Authorization = 'Basic 1233225235'
	*/
	var setNullIfEmpty = function setNullIfEmpty(data){
		if (typeof data !== 'object') {
			return data;
		}
		for (var key in data) {
			if(key.indexOf('$') === -1){
				if (!data.hasOwnProperty(key)){
					continue;
				}
				var value = data[key];
				if (typeof value === 'object') {
					setNullIfEmpty(value);
				}
				else if (typeof value === 'string') {
					data[key] = value === '' ? null : value;
				}
			}
		}
	};
	
    $httpProvider.defaults.transformRequest.unshift(function (data, headers) {
	   	 if(data && angular.isObject(data)){
    		setNullIfEmpty(data);
			if(window.console !== undefined){
    			console.log('Request  : ' + JSON.stringify(data));
    		}
    	}
    	$('#spinner').show();
        return data;
    });
    $httpProvider.defaults.transformResponse.push(function (data, headers) {
		if(window.console !== undefined){
			if(data && angular.isObject(data)){
				console.log('Response : ' + JSON.stringify(data));
			}
		}
        return data;
    });
}]);

/*************************************/
//Global http errors/msgs handler
cargoopsApp.config(['$provide', '$httpProvider', '$compileProvider',function($provide, $httpProvider, $compileProvider) {
        
        var appMessageElement;
        var showErrors = function(errors) {
			if($('[app-messages=\'\']').length > 0){
				$('[app-messages=\'\']').html('');
				if(appMessageElement){
					var UL = $('<UL/>').addClass('errorMessage').appendTo(appMessageElement).text('Validation Error(s)');
					errors.forEach(function(error) {
						 $('<LI/>').appendTo(UL).text(error.message);
					});
				}
			}
        };

        var showMessage = function(msgs,c) {
        	if($('[app-messages=\'\']').length > 0){
				$('[app-messages=\'\']').html('');
				if(appMessageElement){
					var UL = $('<UL/>').addClass(c).appendTo(appMessageElement);
					msgs.forEach(function(msg) {
						 $('<LI/>').appendTo(UL).text(msg);
					});
				}
        	}
        };
        
        $compileProvider.directive('appMessages', function() { 
        	var appMessagesDirective = {
                link: function(scope, element, attrs) { 
                	appMessageElement=$(element); 
                }
            };
            return appMessagesDirective;
        });

        $httpProvider.interceptors.push(['$q',function($q) {
            return {
              	'response': function (response) {
                	/*
                    if (response.config.method != "GET"){ //no need for responses after gets (at least for my uses)
                        showMessage(['Success!'],'successMessage');
                    }
                    else {
                    	console.log(response); //still want to see it in strange cases
                    }
                    */
                     $('#spinner').hide();
                    return response;
                },
                'responseError': function(rejection) {
                	if(rejection.status){
						switch (rejection.status) {
							case 400:  // Bad Request
								if(rejection.data && rejection.data.length > 0){
									showErrors(rejection.data);
								}
								break;
							case 401:  //Unauthorized
								showMessage(['Looks like you\'re not logged in anymore.'], 'successMessage');
								break;
							default :
								showMessage([rejection.statusText], 'errorMessage');
						}
					}
                   	$('#spinner').hide();
                    return $q.reject(rejection);
                }
            };
        }]);
    }]);
/*************************************/
//Console Config
cargoopsApp.config(['$routeProvider',function($routeProvider) {
  $routeProvider.
      when('/', {
        controller: 'ConsoleController',
        templateUrl: 'console/console.html'
      });
}]);

/*************************************/
//FlightPlan Config
cargoopsApp.config(['$routeProvider',function($routeProvider) {
  $routeProvider.
      when('/flightplan', {
        controller: 'FlightPlanController',
        templateUrl: 'flightplan/flightplan.html'
      });
}]);

/*************************************/
//LoadPlan Config
cargoopsApp.config(['$routeProvider',function($routeProvider) {

  $routeProvider.
      when('/loadplan', {
        controller: 'LoadPlanController',
        templateUrl: 'loadplan/loadplan.html'
      });
}]);

/*************************************/
//UldPlan Config
cargoopsApp.config(['$routeProvider',function($routeProvider) {
  $routeProvider.
      when('/uldplan', {
        controller: 'UldPlanController',
        templateUrl: 'uldplan/uldplan.html'
      });
}]);

/*************************************/
//CRUD Config
cargoopsApp.config(['$routeProvider',function($routeProvider) {

  $routeProvider.
      when('/userlist', {
        controller: 'UserListController',
        templateUrl: 'crud/userlist.html'
      }).
      when('/usercreate', {
        controller: 'UserEditController',
        templateUrl: 'crud/useredit.html'
      }).
      when('/useredit/:id', {
    	  controller: 'UserEditController',
          templateUrl: 'crud/useredit.html'
	  });
}]);

/*************************************/

})(); // ends main function