var app = angular.module('app', ['ngRoute']);

    app.config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'webapp/AngularJS/index/templates/home.html',
                controller: 'home-controller'
            })
            .when('/doctors', {
                templateUrl: 'webapp/AngularJS/index/templates/doctor-list.html',
                controller: 'home-controller'
            })  
            .when('/specialties', {
                templateUrl: 'webapp/AngularJS/index/templates/specialty-list.html',
                controller: 'home-controller'
            })                  
            .otherwise({
                redirectTo: '/'
            })
    });