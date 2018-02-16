var app = angular.module("myApp", []);



app.controller('headerController', ["$scope", "myFactory", function($scope, myFactory) {
    
    $scope.header = myFactory;
    
} ]);

app.controller('footerController', ["$scope", "name", function($scope, name){
    
    $scope.footer = name;
    
}]);


app.factory('myFactory' , function (){
    
    return "myFactory called";
    
});
