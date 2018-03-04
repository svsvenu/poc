(
function() {
var app = angular.module('myApp');
   
app.controller("myCtrl", ["$scope",  "ContactDataSvc", function($scope, svc) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $scope.middleName = "middle";
    $scope.nameObj = {"name" : "venu"};
    
    $scope.results = [];
    
     svc.getContacts().then(function(data) {
        
        $scope.results = data.data;
        
        console.log(data);
        
    });
    
    console.log($scope.results);

    
    $scope.selectContact = function(index) {  
        console.log("Called select Contact");
        $scope.current =  $scope.results[index]; } 
    
   
    
   $scope.save = function(current) {
       console.log(current);
        
       svc.saveContacts(current).then(function(data) {
                
        console.log("saved");
        
    });
   }
    
    
} ] );

    
}
)();