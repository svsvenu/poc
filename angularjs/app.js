var express = require('express');
var path = require('path');

var app = express();

// Define the port to run on
app.set('port', 3000);

 app.use(express.static(path.join(__dirname, '.')));

app.get('/rest', function (req, res) {
    
 var ret = {};
    
ret.name = "name";
    
    
 // res.send(JSON.stringify(ret));
    res.send(ret);
});

// Listen for requests
var server = app.listen(app.get('port'), function() {
  var port = server.address().port;
  console.log('Magic happens on port1  ' + port);
});



/* var http = require('http');

var finalhandler = require('finalhandler');

var serveStatic = require('serve-static');

var express = require('express');

// console.log(express);

 var serve = serveStatic("./");

var app = express();

// console.log(app);


app.get('/rest', function (req, res) {
  res.send('Hello World!')
})

var server = http.createServer(function(req, res) {
  var done = finalhandler(req, res);
  serve(req, res, done);
});

server.listen(8000);

*/