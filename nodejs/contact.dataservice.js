(function() {

var app = angular.module('myApp');

app.service('ContactDataSvc', function($http) {
    
    this.getContacts = function () {

    return  $http.get('http://localhost:3000/results');
  
};
    
        this.saveContacts = function (contact) {

    return  $http.put('http://localhost:3000/results/' + contact.id, contact);
  
};
    

    
});
    
    


})();