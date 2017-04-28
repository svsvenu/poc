var dataFactory = angular.module('DataFactory',[]);

dataFactory.factory('userService',function($http){
    
    var fac = {};
    
    fac.users = ['venu', 'jeju', 'chote'];
    
  //  console.log($http);
    
    return fac;
    
    
});

dataFactory.factory('anotherUserService', function($http){
    
   var fac = {};
    
    fac.users = ['venu1', 'jeju1', 'chote1'];
    
    console.log('another user service called');
    return fac;
    
});

angular.module("hashtag").directive('venuDirective', function($interval, $scope){
        
    return {
        
            template: "<b>Hi from {{ message.message }}</b>"

    };
}


);
