function L1 () {}

function L2 () {}

L1.prototype.prop = {value:'L1'};

L1.prototype.prim = 10;

L1.prototype.fun = function() {console.log('i am fun on prototype'); return 'fun';}


 L2.prototype = L1.prototype;

var level1 = new L1();

var level2 = new L2();

var level3 = Object.create(L2.prototype); 


console.log(level1.prop.value);

 level2.prop.value = 'L2'; 

console.log(level1.prop.value);

console.log(level1.prim);

 level2.prim = '11'; 

console.log(level3.__proto__.constructor);





