global.name = "global";


function printName(name ) {

	console.log(name);
	
	console.log (global.name);

}

printName("value");


var cycle = {

"name" : "value",

"getTireCount" : function() { console.log ("called getTireCount" );}

}

function addNumbers() {

console.log("called add numbers");

console.log(this);


}

cycle.addNumbers = addNumbers;

// cycle.addNumbers();

var obj = new addNumbers();

// console.log( obj.__proto__.constructor);

console.log( addNumbers.prototype === obj.__proto__ );





