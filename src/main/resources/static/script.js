
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

function postTargetsInMap(targetArray, baseCurrenciesMap) {
    let htmlTargets = "";  
    targetArray.forEach(element => {
        htmlTargets += "<li>" + element + "    :    " + baseCurrenciesMap.get(element) + "</li>";
        document.getElementById("currencies").innerHTML = htmlTargets;
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

async function primaryFunction(element) {  // previously had async
        const workingAPIMap = await fullAPIFetch(element); // previously have awiat
        
        var rollingMap  =  makeRollingMap(element, getAllCurrencies(), workingAPIMap)
        var jsonResult = mapToJSON(rollingMap)
        sendDataToJava(jsonResult);   
};    

function loopEachMap(){
    getAllCurrencies().forEach(element => {primaryFunction(element)});
}

function getAllCurrencies() {
    let allCurrencyArray = makeBasePlusTargetsArray(grabTargetCurrencies(), grabBaseCurrency())
    return allCurrencyArray;
}

function runJavaMethod(){
    return {
        
    }
}