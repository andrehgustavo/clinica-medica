var app = angular.module('app', ['ngRoute']);

    app.config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'webapp/AngularJS/index/templates/home.html',
                controller: 'home-controller'
            })
            .when('/doctors', {
                templateUrl: 'webapp/AngularJS/index/templates/list-doctors.html',
                controller: 'doctor-controller'
            })  
            .when('/doctors/newDoctor', {
                templateUrl: 'webapp/AngularJS/index/templates/form-doctors.html',
                controller: 'doctor-controller'
            })                  
            .otherwise({
                redirectTo: '/'
            })
    });