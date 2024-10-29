var errorRespo = "field must not be empty";
var inputValue = document.getElementById('searchInput').value;
var dispError = document.getElementById('errors');
var searchBtn = document.getElementById('searchBtn');
function errors (){
    if(inputValue == null || inputValue == ""){
        dispError.innerHTML = errorRespo;
        dispError.style.color ='red';
        console.log("error");

        }
        else{dispError.innerHTML = ""}
   }
  