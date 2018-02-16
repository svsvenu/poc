(
function() {
var app = angular.module('myApp');
   
app.controller("myCtrl", ["$scope",  "ContactDataSvc", function($scope, svc) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $scope.middleName = "middle";
    $scope.nameObj = {"name" : "venu"};
    
     svc.getContacts().then(function(data) {
        
        $scope.results = data.data;
        
        console.log(data);
        
    });

    
    $scope.selectContact = function(index) {  
        console.log("Called select Contact");
        $scope.current =  results[index]; } 
    
   
    
   
    
    
} ] );

    
}
)();