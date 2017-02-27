var app = angular.module("hashtag", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/home", {
        templateUrl : "partials/home.par"
    })
    .when("/news", {
        templateUrl : "partials/news.par"
    })
    .when("/contact", {
        templateUrl : "partials/contact.par"
    })
    
      .when("/about", {
        templateUrl : "partials/about.par"
    })
    
        .when("/mission", {
        templateUrl : "partials/mission.par"
    })
    
    ;
    
});

app.controller('NavCtrl', function($scope, $location) {
    
    $scope.user = {
        
        name : "venu"
        
    };
    
    $scope.isActive = function(route) {
        
        return route === $location.path();
        
     //   $scope.active = "active";
        
      
    };
    
    console.log("user name is " + $scope.user.name);
    
    
});