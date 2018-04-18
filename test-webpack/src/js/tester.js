var nestedObject = {

    "key" : "value",
    "inner1" : {
                    "innerKey1" : "invverValu1"
                },
    "innerArr" : [{'a':'a 1' }, {'b': 'b 1'}, {'c' : 'c 1' }]


};

traverseObj(nestedObject);

function traverseObj(obj) {
for ( i in obj) {

        console.log('key is ' + i);

   
    if ( typeof obj[i] == "string") {

      //  console.log(obj[i]);

    }

    if ( typeof obj[i] == "object") {

        traverseObj(obj[i]);

    }

}
}