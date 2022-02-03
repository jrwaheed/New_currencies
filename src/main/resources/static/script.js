import {createScatterPlotButtons} from '/src/main/resources/static/SQLGetScatterPlotData.js';

function grabBaseCurrency () {
    var baseCurrencySelection = document.getElementById("userBaseInput").value;
    return baseCurrencySelection
}


function grabTargetCurrencies() {
    const targetArray = [];
    for (let i = 1; i <= 3; i++) {
        let targetNumber = "";
        targetNumber = "dropDownEligible" + i;
        if (document.getElementById(targetNumber).value != "Select a Currency") {
            targetArray.push(document.getElementById(targetNumber).value)
        } else { }

    }
    return targetArray;
};

function makeBasePlusTargetsArray(targetArray, baseCurrency){
    let allCurrencyArray = [];   
    allCurrencyArray.push(baseCurrency);
    for(let i = 0; i < targetArray.length; i++){
        allCurrencyArray.push(targetArray[i])
    }
    return allCurrencyArray
};

async function fullAPIFetch(element) {
    var urlCurrenciesBase = 'https://api.coinbase.com/v2/exchange-rates?currency=' + element;
    var baseCurrenciesMap = new Map();
    return fetch(urlCurrenciesBase, { method: "GET" })
        .then(response => response.json())
        .then(function (result) {
            var obj = result.data.rates;
            var keys = Object.keys(obj);
            for (var val in keys) {
                baseCurrenciesMap.set(keys[val], obj[keys[val]]);
            }
            console.log(baseCurrenciesMap.keys)
            console.log(baseCurrenciesMap.values)
            return baseCurrenciesMap;
        });
};


function makeRollingMap (element, allCurrencyArray, workingAPIMap){
    const rollingMap = new Map();

    var myIndex = allCurrencyArray.indexOf(element);

    if(myIndex !== -1){
        allCurrencyArray.splice(myIndex, 1)
    }

    console.log(allCurrencyArray)
    
    rollingMap.set(element, 1.00)

    allCurrencyArray.forEach(item => {
        rollingMap.set(item, workingAPIMap.get(item))
    })
    return rollingMap;
};

function mapToJSON(rollingMap) {
    let jsonObject = {}

    rollingMap.forEach((value, key)=> {
        jsonObject[key] = value
    })

    let JSONtargets = JSON.stringify(jsonObject);  
        console.log(JSONtargets)
    return JSONtargets;  
}

function sendDataToJava(jsonResult){
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'POST',
        url:'http://localhost:8080/index1',
       
        data: jsonResult,
        success: console.log("Target currencies sent"),
        error : onerror
    });
}

async function primaryFunction(element) {  
        const workingAPIMap = await fullAPIFetch(element); 
        
        var rollingMap  =  makeRollingMap(element, getAllCurrencies(), workingAPIMap)
        var jsonResult = mapToJSON(rollingMap)
        sendDataToJava(jsonResult);
        
};    


function getAllCurrencies() {
    let allCurrencyArray = makeBasePlusTargetsArray(grabTargetCurrencies(), grabBaseCurrency())
    return allCurrencyArray;
}

async function bigRun(){
    const delay = async (ms = 500) => new Promise(resolve => setTimeout(resolve, ms));
    
    for(const element of getAllCurrencies()){
        await primaryFunction(element);
        await delay(100);
    }   
    
    SQLBuildOut();
    await delay(100)
    SQLFindArbitrage();
    

}



function SQLBuildOut(){
    var val ="dummy string"

    $.ajax({  
        type: 'GET',
        url:'http://localhost:8080/index2',
        //url:'http://localhost:5500/index2',

        async: true,
        cache: false,
       
        success: console.log("SQL buildOut completed"),
        error : onerror
    })
    setTimeout(function(){ createScatterPlotButtons(); }, 2500);
}

function SQLFindArbitrage(){
    var val ="dummy string"

    $.ajax({  
        type: 'GET',
        url:'http://localhost:8080/index3',
        
        async: true,
        cache: false,
       
        success: console.log("SQL FindArbitrage completed"),
        error : onerror
    })
}

////////////////////////////////////////////////////////////////////////

function updateDataToJava(jsonResult){
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'POST',
        url:'http://localhost:8080/index8',
       
        data: jsonResult,
        success: console.log("Target currencies sent"),
        error : onerror
    });
}

const initiateButtonToggle = document.getElementById("baseButton");


initiateButtonToggle.addEventListener("click", clickInitiateButtonEvent);

function clickInitiateButtonEvent(){
    if (document.getElementById("selectedCombos").hasChildNodes){
        document.getElementById("baseButton").disabled = true;
    } else {
        document.getElementById("baseButton").disabled = true;
    }
}


//export {SQLBuildOut}
window.bigRun = bigRun;
window.SQLBuildOut = SQLBuildOut;
window.SQLFindArbitrage = SQLFindArbitrage;
window.updatedDataToJava = updateDataToJava;
