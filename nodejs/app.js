var movies = require('./movies.js')

var fs = require('fs');

var http = require('http');

var person = {
    
    firstName : "venu",
    
    lastName : "surampudi",
    
    printName : function() { 
                    
                    console.log(this == person)
                    
                }
    
    
};

var callBack = function(){
    
    console.log ("called back");
};

function printName(){
    
    console.log(this == person)
}

// setTimeout(callBack, 5000);

person.printName();

printName();

// console.log(module);

console.log(movies.avatar);

movies.avatar();

http.createServer(processReq).listen(8000);

function processReq(request, response){
    
  response.writeHead(500, {'Content-Type': 'text/plain'});
   
   // Send the response body as "Hello World"
   response.end('Hello World\n');    
}