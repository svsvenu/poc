var movies = require('./movies.js')

var fs = require('fs');

var http = require('http');

var person = {
    
    firstName : "venu",
    
    lastName : "surampudi",
    
    printName : function() { {
   "results":[
      {
         "gender":"male",
         "name":{
            "title":"mr",
            "first":"jersey",
            "last":"ter veer"
         },
         "location":{
            "street":"4628 abstederdijk",
            "city":"heerlen",
            "state":"zuid-holland",
            "postcode":66614
         },
         "email":"jersey.terveer@example.com",
         "login":{
            "username":"blackwolf766",
            "password":"morning",
            "salt":"iS9hgt0V",
            "md5":"f606d5f3e80be5af5d2adf63fcda2cc1",
            "sha1":"3b9bc21b9548c6d8927ca50cfebbc3302ea9b672",
            "sha256":"5770dce7522ba8f24c99ee0fe13015da7efe4dbaa00093f1e188f3e8f0d54449"
         },
         "dob":"1956-05-04 10:42:36",
         "registered":"2013-11-26 04:39:09",
         "phone":"(683)-229-0716",
         "cell":"(850)-951-4696",
         "id":1,
         "picture":{
            "large":"https://randomuser.me/api/portraits/men/67.jpg",
            "medium":"https://randomuser.me/api/portraits/med/men/67.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/men/67.jpg"
         },
         "nat":"NL"
      },
      {
         "gender":"female",
         "name":{
            "title":"mrs",
            "first":"lumi",
            "last":"laurila"
         },
         "location":{
            "street":"6742 hämeenkatu",
            "city":"nastola",
            "state":"uusimaa",
            "postcode":57713
         },
         "email":"lumi.laurila@example.com",
         "login":{
            "username":"yellowelephant379",
            "password":"froggie",
            "salt":"4kmr7XRy",
            "md5":"6aa4477f7c540cf164bd267f7c183c3f",
            "sha1":"18462d95e3837322f70e50f5c6790ad325689490",
            "sha256":"3d31c6b27656a31f951c7d3bb64f04a23d3fd982cc77f188abeb8c7b7a25980c"
         },
         "dob":"1954-05-02 03:16:53",
         "registered":"2008-02-06 19:46:37",
         "phone":"09-223-553",
         "cell":"047-262-22-62",
         "id":2,
         "picture":{
            "large":"https://randomuser.me/api/portraits/women/93.jpg",
            "medium":"https://randomuser.me/api/portraits/med/women/93.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/women/93.jpg"
         },
         "nat":"FI"
      },
      {
         "gender":"male",
         "name":{
            "title":"mr",
            "first":"سام",
            "last":"حسینی"
         },
         "location":{
            "street":"4362 پارک شریعتی",
            "city":"آمل",
            "state":"یزد",
            "postcode":83341
         },
         "email":"سام.حسینی@example.com",
         "login":{
            "username":"smallmouse468",
            "password":"citroen",
            "salt":"96VuGjs9",
            "md5":"dc62e26af0727a80cc0ae8131197bc44",
            "sha1":"abbed5709709957a96cac770a4f32261dd4ab835",
            "sha256":"ab4ae44fc1e8bf23362c1247bbb289a05c88186cccb9f33b2b8426fe1576d47d"
         },
         "dob":"1993-07-02 00:35:51",
         "registered":"2014-03-05 05:11:49",
         "phone":"069-25657052",
         "cell":"0909-377-2209",
         "id":3,
         "picture":{
            "large":"https://randomuser.me/api/portraits/men/88.jpg",
            "medium":"https://randomuser.me/api/portraits/med/men/88.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/men/88.jpg"
         },
         "nat":"IR"
      },
      {
         "gender":"male",
         "name":{
            "title":"mr",
            "first":"آرسین",
            "last":"صدر"
         },
         "location":{
            "street":"6523 شهید اکبر وصالی",
            "city":"یزد",
            "state":"هرمزگان",
            "postcode":67629
         },
         "email":"آرسین.صدر@example.com",
         "login":{
            "username":"ticklishleopard721",
            "password":"jesus1",
            "salt":"Dswf0YwC",
            "md5":"b77de944980f313f79da92c668e40648",
            "sha1":"8bcbcd05cfa31d1be868878673399750ef0c6aa4",
            "sha256":"026b2a4bd4937b599f9a8b1aeb3982b748e4804e589da59ac431de9a57569aa1"
         },
         "dob":"1962-11-20 07:58:17",
         "registered":"2007-12-21 12:04:36",
         "phone":"064-00018915",
         "cell":"0999-951-6273",
         "id":4,
         "picture":{
            "large":"https://randomuser.me/api/portraits/men/19.jpg",
            "medium":"https://randomuser.me/api/portraits/med/men/19.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/men/19.jpg"
         },
         "nat":"IR"
      },
      {
         "gender":"male",
         "name":{
            "title":"mr",
            "first":"veeti",
            "last":"hokkanen"
         },
         "location":{
            "street":"4382 nordenskiöldinkatu",
            "city":"suomussalmi",
            "state":"south karelia",
            "postcode":31371
         },
         "email":"veeti.hokkanen@example.com",
         "login":{
            "username":"brownbear196",
            "password":"semperfi",
            "salt":"zyzgf39f",
            "md5":"2e309dbc0e67b42d0db7f3c5f063f257",
            "sha1":"636ce43976476dd5cff76767fa188f848ad1c966",
            "sha256":"89534534da3f9c9cb4b1f99cd5d165b7eadc84d069ac132c38ab41027419e3cf"
         },
         "dob":"1983-02-12 06:28:41",
         "registered":"2004-12-08 02:20:07",
         "phone":"06-635-732",
         "cell":"045-826-01-55",
         "id":5,
         "picture":{
            "large":"https://randomuser.me/api/portraits/men/44.jpg",
            "medium":"https://randomuser.me/api/portraits/med/men/44.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/men/44.jpg"
         },
         "nat":"FI"
      },
      {
         "gender":"male",
         "name":{
            "title":"mr",
            "first":"aapo",
            "last":"linna"
         },
         "location":{
            "street":"7380 satakennankatu",
            "city":"kaskinen",
            "state":"päijät-häme",
            "postcode":29646
         },
         "email":"aapo.linna@example.com",
         "login":{
            "username":"greenswan305",
            "password":"potato",
            "salt":"U4qaDUve",
            "md5":"a002a9f508e1bf00ab76514fb967a7a6",
            "sha1":"406b17e2b07bb16d65b9e566465b69985f1db617",
            "sha256":"cbc3a6b53df22d59c2f7aa8d77562daf7bbbfe7e0d59ad7fd8c2058bec94f981"
         },
         "dob":"1976-08-06 06:38:47",
         "registered":"2015-06-10 11:14:10",
         "phone":"02-106-735",
         "cell":"041-366-54-55",
         "id":6,
         "picture":{
            "large":"https://randomuser.me/api/portraits/men/34.jpg",
            "medium":"https://randomuser.me/api/portraits/med/men/34.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/men/34.jpg"
         },
         "nat":"FI"
      },
      {
         "gender":"male",
         "name":{
            "title":"mr",
            "first":"maxwell",
            "last":"pierce"
         },
         "location":{
            "street":"4832 victoria street",
            "city":"worcester",
            "state":"gwent",
            "postcode":"GR26 7LL"
         },
         "email":"maxwell.pierce@example.com",
         "login":{
            "username":"brownbear480",
            "password":"hhhh",
            "salt":"hdOm9T6x",
            "md5":"a89d9a7eb942792091cb70c08c1eff1c",
            "sha1":"1e2229fe1617486e7d53d5b1bd38bed7f8cf6742",
            "sha256":"f6c095dbe9ee61ab12d062d018db888d99ad6953a9a80d106ab0693e3db6480a"
         },
         "dob":"1953-01-21 22:13:17",
         "registered":"2003-12-21 09:46:26",
         "phone":"0118483 427 9745",
         "cell":"0772-511-008",
         "id":7,
         "picture":{
            "large":"https://randomuser.me/api/portraits/men/22.jpg",
            "medium":"https://randomuser.me/api/portraits/med/men/22.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/men/22.jpg"
         },
         "nat":"GB"
      },
      {
         "gender":"female",
         "name":{
            "title":"mrs",
            "first":"emily",
            "last":"andersen"
         },
         "location":{
            "street":"1276 grønlandsparken",
            "city":"klitmøller",
            "state":"hovedstaden",
            "postcode":37587
         },
         "email":"emily.andersen@example.com",
         "login":{
            "username":"organicpanda326",
            "password":"helpme",
            "salt":"xbC0zOuT",
            "md5":"4798d632e2f8c82d82b911b9a18ac6cb",
            "sha1":"24294ca849e77c255c5d34d471e75164ef07fdff",
            "sha256":"716a070d053e613a6790cb2eced25ac5654383337434c3cc1c86a557c4fa0e35"
         },
         "dob":"1970-10-07 07:16:26",
         "registered":"2005-12-26 04:13:16",
         "phone":"84103701",
         "cell":"71520811",
         "id":8,
         "picture":{
            "large":"https://randomuser.me/api/portraits/women/12.jpg",
            "medium":"https://randomuser.me/api/portraits/med/women/12.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/women/12.jpg"
         },
         "nat":"DK"
      },
      {
         "gender":"male",
         "name":{
            "title":"mr",
            "first":"hunter",
            "last":"medina"
         },
         "location":{
            "street":"7552 daisy dr",
            "city":"broken arrow",
            "state":"idaho",
            "postcode":64128
         },
         "email":"hunter.medina@example.com",
         "login":{
            "username":"lazybird531",
            "password":"truck",
            "salt":"Rp5oa3BI",
            "md5":"7c18c7a2b8cb2837f40ab9939d010e0a",
            "sha1":"62ef83b8f103c926e16c9ea07d324bd33532260c",
            "sha256":"c496a2980c55fad70d3546195c0608f9b20fdbc6afa46112ad4bf296eb8ef7d0"
         },
         "dob":"1958-08-09 12:30:23",
         "registered":"2016-01-26 12:34:21",
         "phone":"(521)-179-5583",
         "cell":"(367)-065-8720",
         "id":9,
         "picture":{
            "large":"https://randomuser.me/api/portraits/men/22.jpg",
            "medium":"https://randomuser.me/api/portraits/med/men/22.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/men/22.jpg"
         },
         "nat":"US"
      },
      {
         "gender":"female",
         "name":{
            "title":"mrs",
            "first":"lena",
            "last":"oliveira"
         },
         "location":{
            "street":"5134 rua são luiz ",
            "city":"imperatriz",
            "state":"rio grande do norte",
            "postcode":43926
         },
         "email":"lena.oliveira@example.com",
         "login":{
            "username":"purpletiger271",
            "password":"sneakers",
            "salt":"AtXRYJuY",
            "md5":"d427d4effa0e012e496da49198fee676",
            "sha1":"2253f3f6e1d55eff156d3cbb3ec9b0ef7a387409",
            "sha256":"c424f9f2435641545868e953367d1b171fe99947b42800fa43b13e965220f84a"
         },
         "dob":"1967-11-01 13:04:46",
         "registered":"2016-03-10 13:35:57",
         "phone":"(50) 3863-9647",
         "cell":"(44) 8389-5710",
         "id":10,
         "picture":{
            "large":"https://randomuser.me/api/portraits/women/86.jpg",
            "medium":"https://randomuser.me/api/portraits/med/women/86.jpg",
            "thumbnail":"https://randomuser.me/api/portraits/thumb/women/86.jpg"
         },
         "nat":"BR"
      }
   ],
   "info":{
      "seed":"7bfeb819d0e1cfc7",
      "results":10,
      "page":1,
      "version":"1.1"
   }
}
                    
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