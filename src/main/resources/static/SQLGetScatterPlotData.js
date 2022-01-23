//import { SQLBuildOut} from '/src/main/resources/static/script.js';

function SQLGetScatterData(){
    var ScatterCombos = [];

    $.ajax({  
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'GET',
        url:'http://localhost:8080/index5',
        
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function(response){
        ScatterCombos = response;
        }
    });
    for(var i = 0; i < ScatterCombos.length; i++){
        console.log(ScatterCombos[i])
    };
    return ScatterCombos;
};



async function createScatterPlotButtons(){ 
 


   

    
    var floatingScatterCombos = document.getElementById("selectedCombos");
    const ul = document.getElementById("selectedCombos");

    var ScatterCombos = await SQLGetScatterData();

    let fullScatterArray = ScatterCombos.map(element => element.legOne);

    for(var i = 0; i < fullScatterArray.length; i++){
        var opt = fullScatterArray[i];

        var li = document.createElement("button");
        li.innerHTML = fullScatterArray[i];
        ul.appendChild(li)    
     }

     for(var i = 0; i <= fullScatterArray.length; i++){
        
        document.getElementById("selectedCombos").childNodes[i].className = "btn btn-outline-secondary";

        //document.getElementById("selectedCombos").childNodes[i].onclick = function() {testFunction(this.textContent)};
    }
} 

function testFunction(scatterSelection){
   
   alert(scatterSelection)

  
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'POST',
        url:'http://localhost:8080/index6',
       
        data: scatterSelection,
        success: console.log("Scatter selection sent"),
        error : onerror
    });
}


export {createScatterPlotButtons}
window.createScatterPlotButtons = createScatterPlotButtons;