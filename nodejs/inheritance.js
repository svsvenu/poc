function funky (a) {

    console.log(arguments[0]);

    console.log(a);
    a.p ="8";
}

var f = new funky('a');

console.log (f.__proto__);

console.log ( funky.prototype );

var x = { a : "s"};

funky(x); 

console.log(x);

var x  = () =>  console.log('i am x') ; 

x(); 