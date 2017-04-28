var app = angular.module("hashtag", ["ngRoute","DataFactory"]);

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

app.controller('NavCtrl', function($rootScope, $scope, $location, userService, anotherUserService, $interval) {
    
    $scope.user = {
        
        name : "venu"
        
    };
    
    
    $rootScope.message = "venu";
    
    $scope.setUser = function() { $scope.user.name = "jeju";}
    
    $scope.isActive = function(route) {
        
        return route === $location.path();
        
     //   $scope.active = "active";
        
      
    };
    
   // $interval(function () {
     //       $scope.message = "Timeout called!";
       //     console.log("user name is called" );
        
           //  $scope.$apply();


//        }
  //  , 2000);
    
    console.log("user name is " + $scope.user.name);
    
    console.log("userrs " + userService.users);
    
   $rootScope.promise = $interval(
        
        function() {
            
         //   $scope.setUser();
            
      //                          console.log("before");

            
        //                        console.log($scope.message);

            var d = new Date();

            
           $rootScope.message = d.getTime();
            
            
                    console.log($scope.message);

            
            
        }
        , 300);


    
        console.log("new1 interval " );

    
        console.log("another users " + anotherUserService.users);
    
            console.log("user is  " + $scope.user.name);

    

    
    
}); 



