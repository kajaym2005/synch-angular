(function () {
'use strict';

/*************************************/
//HTML5 Web Storage - window.localStorage - stores data with no expiration date
angular.module('cargoopsApp').factory('aswLocalStorage', ['$rootScope','localStorage',function($rootScope,localStorage) {
	return {
		storeProperty: function(localName, storageName, defaultValue) {
			var json = localStorage[storageName];
			$rootScope[localName] = json ? JSON.parse(json) : defaultValue;
			$rootScope.$watch(
				function() { return $rootScope[localName]; },
				function(value) {
				  if (value) {
					localStorage[storageName] = JSON.stringify(value);
				  }
				},
			true);
		}
	};
}]);

/*************************************/
//HTML5 Web Storage - stores data for one session and data is lost when the tab is closed.
angular.module('cargoopsApp').factory('aswSession', ['$rootScope','sessionStorage',function($rootScope,sessionStorage) {
	return {
		storeProperty: function(localName, storageName, defaultValue) {
			var json = sessionStorage[storageName];
			$rootScope[localName] = json ? JSON.parse(json) : defaultValue;
			$rootScope.$watch(
				function() { return $rootScope[localName]; },
				function(value) {
				  if (value) {
					sessionStorage[storageName] = JSON.stringify(value);
				  }
				},
			true);
		}
	};
}]);

/*************************************/
angular.module('cargoopsApp').factory('aswCalendar', ['$rootScope','appConfig',function($rootScope,appConfig) {
	return {
		config: function (){
			return {
				opened: {},
				dateFormat: appConfig.dateFormat,
				dateOptions: {'startingDay':1},
				open: function($event, which) {
					$event.preventDefault();
					$event.stopPropagation();
					this.closeAll(which);
					this.opened[which] = !this.opened[which];
				},
				closeAll: function(which){
					for(var key in this.opened) {
						if(key !== which){
							this.opened[key] = false;
						}
					}
				}
			};
		}
	};
}]);

/*************************************/
angular.module('cargoopsApp').provider('aswLog', function() {
	 //DateTime,Context,log
    this.loggingPattern = '{0} : {1} : {2}';
    this.logLevel=1; //1=debug,2=info,3=warn,4=error
    this.$get = ['$filter',function($filter) {
    	var loggingPattern = this.loggingPattern;
    	var logLevel = this.logLevel;
    	return {
    		enhanceAngularLog: function ($log) {
 				$log.getInstance = function (context) {
                    return {
                        log		: enhancedLog($log.log, context,0),
                        debug	: enhancedLog($log.debug, context,1),
                        info	: enhancedLog($log.info, context,2),
                        warn	: enhancedLog($log.warn, context,3),
                        error	: enhancedLog($log.error, context,4)
                    };
                };
				function enhancedLog(loggingFn, context,level) { 	
					 return function() {
					 	if(level === 0 || logLevel <= level ){
							var args = [].slice.call(arguments);
							var dateStr = $filter('date')(new Date(),'h:mm:ss a');
							args[0] = String.format(loggingPattern,dateStr,context,args[0]);
							loggingFn.apply(null, args);  
					 	}
					 };
				}
    		}
    	};
    }];
});

/*************************************/
String.format = function() {
    // The string containing the format items (e.g. "{0}") will and always has to be the first argument.
    var theString = arguments[0];
    // start with the second argument (i = 1)
    for (var i = 1; i < arguments.length; i++) {
        // "gm" = RegEx options for Global search (more than one instance) and for Multiline search
        var regEx = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        theString = theString.replace(regEx, arguments[i]);
    }
    return theString;
};
/*************************************/


})(); // ends main function