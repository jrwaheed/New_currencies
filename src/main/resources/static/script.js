
function grabBaseCurrency () {
    var baseCurrencySelection = document.getElementById("userBaseInput").value;
    return baseCurrencySelection
}


function grabTargetCurrencies() {
    const targetArray = [];
    for (let i = 1; i <= 10; i++) {
        let targetNumber = "";
        targetNumber = "target" + i;
        if (document.getElementById(targetNumber).value != "") {
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



function runThroughBaseAndTargetsFromAPI(allCurrencyArray){
    allCurrencyArray.forEach(element => {
        runTheAPIForAcurrency(element)  
    });
}

async function runTheAPIForAcurrency (runningCurrency){
    var urlCurrenciesBase = 'https://api.coinbase.com/v2/exchange-rates?currency=' + runningCurrency;
    var currencyMap = new Map();
    return fetch(urlCurrenciesBase, { method: "GET" })
        .then(response => response.json())
        .then(function (result) {
            var obj = result.data.rates;
            var keys = Object.keys(obj);
            for (var val in keys) {
                currencyMap.set(keys[val], obj[keys[val]]);
            }
            return currencyMap;
        });
};





async function fullAPIFetch(element) {
    //var baseCurrencySelection = document.getElementById("userBaseInput").value;
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





function postTargetsInMap(targetArray, baseCurrenciesMap) {
    let htmlTargets = "";  
    targetArray.forEach(element => {
        htmlTargets += "<li>" + element + "    :    " + baseCurrenciesMap.get(element) + "</li>";
        document.getElementById("currencies").innerHTML = htmlTargets;
    });
};


function makeTargetMap (targetArray, workingAPIMap){
    const targetMap = new Map();


    targetArray.forEach(item => {
        targetMap.set(item, workingAPIMap.get(item))
    })
    return targetMap;
};



function mapToJSON(targetMap, element) {

    //var baseCurrencySelection = document.getElementById("userBaseInput").value;
    
    let tempMap = new Map();
    let jsonObject = {}

    tempMap.set(element, 1.00)

    targetMap.forEach((value, key) => {
        tempMap.set(key, value)})

    tempMap.forEach((value, key)=> {
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


async function primaryFunction(element, loopCurrencyArray) {
    
      
        const workingAPIMap = await fullAPIFetch(element);
        
        var targetMap  =  makeTargetMap(grabTargetCurrencies(), workingAPIMap)
        var jsonResult = mapToJSON(targetMap, element)
        sendDataToJava(jsonResult);   

   // postTargetsInMap(grabTargetCurrencies(), grandMap)

};    

function loopEachMap(){

    let grandMap = new Map();
    let loopCurrencyArray = makeBasePlusTargetsArray(grabTargetCurrencies(), grabBaseCurrency())
   
    loopCurrencyArray.forEach(element => {primaryFunction(element, loopCurrencyArray)
    });

   
}