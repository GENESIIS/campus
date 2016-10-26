/**
 * Created by tharaka on 10/24/2016.
 */

angular.module('CampusApp',['ngRoute']) 

    .config(function ($routeProvider) {
        $routeProvider
            .when('/',{
                templateUrl : "partials/landing.html"
            })
            .when('/about',{
                templateUrl : "partials/about.html"
            })
            .when('/contact',{
                templateUrl : "partials/contact.html"
            })
            .when('/courses',{
                templateUrl : "partials/courses.html"
            });
    });