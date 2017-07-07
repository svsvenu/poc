var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost/test');

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
  process();
});

function process() {
    
    var kittySchema = mongoose.Schema({
    name: String,
    type:String,
    abs : function(){ return 'test';}
});
    
    console.log(kittySchema);
    
    var Kitten = mongoose.model('Kitten', kittySchema);
    
    var fluffy = new Kitten({ name: 'fluffy' });

    
    fluffy.save(function (err, fluffy) {
  if (err) return console.error(err);
});

    
}