var app = angular.module('app', ['ngRoute']);

    app.config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/webapp/AngularJS/index/templates/home.html',
                controller: 'home-controller'
            })            
            .otherwise({
                redirectTo: '/'
            })
    });