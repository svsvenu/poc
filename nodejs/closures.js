const util = require('util');

function Person(name, ssn) {
    
    this.name = name;
    this.ssn = ssn;
    
}

function Employee(department, city){
    this.department = department;
    this.city = city;    
    this.getDepartment = function() { console.log("department is " + department)}

}

function Manager(teamName, employeeCount) {
    
    this.teamName = teamName;
    this.employeeCount = employeeCount;
    
    console.log("Manager called");
    
}

Manager.prototype.prop = "Manager";

Manager.prototype.__proto__ = Employee.prototype;

Employee.prototype.__proto__ = Person.prototype;

// Employee.prototype.__proto__ = 

var manager = new Manager("java", 10);

var manager = Object.create(Manager);

var $ = function(elem) { console.log("$ called"); elem();}

$(Manager);

// myManager.__proto__ = Employee.prototype;

// console.log(manager.__proto__.__proto__.__proto__);

for (var prop in manager) {
    console.log(prop);
}