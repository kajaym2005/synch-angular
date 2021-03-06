<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%
	String contextPath = "\"" + request.getContextPath() + "\"";
%>
<!doctype html>
<html lang="en" ng-app="cargoopsApp">
	<head>
		<meta charset="utf-8">
		<title>Cargo+Ops Console</title>
		<link rel="stylesheet" href="<c:url value="/bower_components/bootstrap/dist/css/bootstrap.min.css"/>">
		<link rel="stylesheet" href="<c:url value="/bower_components/bootstrap/dist/css/bootstrap-theme.min.css"/>">
		<link rel="stylesheet" href="<c:url value="/styles/application.master.css"/>">
		
		<script type="text/javascript">
			var appParams = {};
			appParams.dateFormat='MM/dd/yyyy';
			
			appParams.USER_API_URL = <%=contextPath%> + '/services/users';
			appParams.USER_API_URL_ID = <%=contextPath%> + '/services/users/:id';
		</script>
	</head>
	<body>
		<div class="container">
			<div class="span6" ng-controller="NavbarController">
				<a href="#/" 			ng-class="{active: routeIs('/')}" 			role="button" class="btn btn-primary">Home</a>
				<a href="#/flightplan"  ng-class="{active: routeIs('/flightplan')}" role="button" class="btn btn-primary">Flight Plan</a>
				<a href="#/loadplan"    ng-class="{active: routeIs('/loadplan')}"   role="button" class="btn btn-primary">Load Plan</a>
				<a href="#/uldplan"     ng-class="{active: routeIs('/uldplan')}"    role="button" class="btn btn-primary">Build Plan</a>
				<a href="#/userlist"    ng-class="{active: routeIs('/userlist')}"   role="button" class="btn btn-primary">User - CRUD</a>
			</div>
			<br/>
			<img id="spinner" ng-src="../images/spinner.gif" style="display:block;">
			
			<ng-view></ng-view>
		</div>

		<!-- JS Library -->
		<script type="text/javascript" src="<c:url value="/bower_components/jquery/dist/jquery.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bower_components/angular/angular.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bower_components/angular-route/angular-route.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bower_components/angular-resource/angular-resource.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bower_components/angular-bootstrap/ui-bootstrap-tpls.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bower_components/angucomplete-alt/angucomplete-alt.js" />"></script>

		<!-- App -->
		<script type="text/javascript" src="<c:url value="/modules/cargoopsApp.js" />"></script>
		
		<!-- Common -->
		<script type="text/javascript" src="<c:url value="/modules/common/directives/CommonDirectives.js" />"></script>
		<script type="text/javascript" src="<c:url value="/modules/common/filters/CommonFilters.js" />"></script>
		<script type="text/javascript" src="<c:url value="/modules/common/services/CommonService.js" />"></script>

		<!-- Console-->
		<script type="text/javascript" src="<c:url value="/modules/console/controllers/ConsoleController.js" />"></script>
		<script type="text/javascript" src="<c:url value="/modules/console/services/ConsoleService.js" />"></script>

		<!-- FlightPlan -->
		<script type="text/javascript" src="<c:url value="/modules/flightplan/controllers/FlightPlanController.js" />"></script>
		<script type="text/javascript" src="<c:url value="/modules/flightplan/services/FlightPlanService.js" />"></script>

		<!-- LoadPlan -->
		<script type="text/javascript" src="<c:url value="/modules/loadplan/controllers/LoadPlanController.js" />"></script>
		<script type="text/javascript" src="<c:url value="/modules/loadplan/services/LoadPlanService.js" />"></script>

		<!-- Uld Plan -->
		<script type="text/javascript" src="<c:url value="/modules/uldplan/controllers/UldPlanController.js" />"></script>
		<script type="text/javascript" src="<c:url value="/modules/uldplan/services/UldPlanService.js" />"></script>
		
		<!-- CRUD -->
		<script type="text/javascript" src="<c:url value="/modules/crud/controllers/UserController.js" />"></script>
		<script type="text/javascript" src="<c:url value="/modules/crud/services/UserService.js" />"></script>
		
	</body>
</html>
