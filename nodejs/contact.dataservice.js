(function() {

var app = angular.module('myApp');

app.service('ContactDataSvc', function($http) {
    

    this.getContacts = function () {

    return  $http.get('http://localhost:3000/results');
 

    
};
    
});


})();