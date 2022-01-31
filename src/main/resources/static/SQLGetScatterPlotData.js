import {startScatterChart} from '/src/main/resources/static/scatterChart.js';

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

function clearOldScatterButtons(){
    const ul = document.getElementById("selectedCombos");
    if(!ul.hasChildNodes()){
        createScatterPlotButtons()

    } else {
        
        ul.remove();
        const ul = document.createElement("selectedCombos");
        createScatterPlotButtons()
    }
}

async function createScatterPlotButtons(){ 

    var floatingScatterCombos = document.getElementById("selectedCombos");
    const ul = document.getElementById("selectedCombos");

    var ScatterCombos = await SQLGetScatterData();

    let fullScatterArray = ScatterCombos.map(element => element.legOne);
    let correctedFullScatterArray = ScatterCombos.map(element => element.legOne);

    for(var i = 0; i < fullScatterArray.length; i++){
        var opt = fullScatterArray[i];

        var li = document.createElement("button");

            li.innerHTML = fullScatterArray[i];
            ul.appendChild(li)        
     }

     for(var i = 0; i <= correctedFullScatterArray.length; i++){
        
        document.getElementById("selectedCombos").childNodes[i].className = "btn btn-outline-secondary";
      
        document.getElementById("selectedCombos").childNodes[i].onclick = function() {prepForScatter(this.textContent)};
    }

} 



function AJAXScatterButton(scatterSelection){
    var result = [];
  
    
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'GET',
        url:'http://localhost:8080/index6',

        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        data: {'scatterSelection': scatterSelection},
        success: function(response){   
            result = response;   
            console.log("ajax for scatter worked")
        }
    });   
    return result
};

    
async function prepForScatter(scatterSelection){

    var scatterValueList = [];
    var scatterComboList = [];
    var scatterTimeList = [];
    var objectList = [];
    var scatterLabelsList = []
    
    const AJAXScatterButtonResponse = await AJAXScatterButton(scatterSelection); 
    
    for(var i=0; i < AJAXScatterButtonResponse.length; i++){
        scatterValueList.push(AJAXScatterButtonResponse[i].value)
        scatterComboList.push(AJAXScatterButtonResponse[i].combo)
        scatterLabelsList.push(AJAXScatterButtonResponse[i].time)

        var tempTime = (AJAXScatterButtonResponse[i].time)
        //const [hours, minutes, seconds] = tempTime.split(':');
        // const totalSeconds = (+hours) * 60 * 60 + (+minutes) * 60 + (+seconds);

        //scatterTimeList.push(totalSeconds)
        scatterTimeList.push(tempTime)
    }

    for(var i = 0; i < scatterComboList.length; i++){
       
        
        const scatterXY = new Object();
        scatterXY.x = scatterTimeList[i];
        scatterXY.y = scatterValueList[i];
        objectList.push(scatterXY)
        
        
    }
    startScatterChart(scatterLabelsList, scatterComboList, objectList)
}


    



export {createScatterPlotButtons}


window.createScatterPlotButtons = createScatterPlotButtons;
window.clearOldScatterButtons = clearOldScatterButtons;
