var mongoose = require('mongoose');

var schema = mongoose.Schema;

var BookSchema = new schema({
    
    name: String,
    published : {
                    
                    type : Date,
                    default :Date.now
                    
                }
    
});

module.exports = mongoose.model('Book', BookSchema);

