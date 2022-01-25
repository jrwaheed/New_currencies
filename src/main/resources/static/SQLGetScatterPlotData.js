import {initiateScatterChart} from '/src/main/resources/static/scatterChart.js';

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

        document.getElementById("selectedCombos").childNodes[i].onclick = function() {AJAXScatterButton(this.textContent)};
    }

} 



function AJAXScatterButton(scatterSelection){
    //var scatterPoints ="";
    var scatterValueList = [];
    var scatterComboList = [];
    var scatterTimeList = [];
    var fullScatterDataListList = []
    
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'GET',
        url:'http://localhost:8080/index6',

        contentType: "application/json; charset=utf-8",
        dataType: "json",
       
        data: {'scatterSelection': scatterSelection},
        success: function(response){
           
            //alert(response[0].value)
            for(var i=0; i < response.length; i++){
                scatterValueList.push(response[i].value)
                scatterComboList.push(response[i].combo)
                scatterTimeList.push(response[i].time)
            }
        }
            
    });
    fullScatterDataListList.push(scatterComboList)
    fullScatterDataListList.push(scatterValueList)
    fullScatterDataListList.push(scatterTimeList)

    initiateScatterChart(scatterComboList, scatterValueList)
};


export {createScatterPlotButtons}
window.createScatterPlotButtons = createScatterPlotButtons;