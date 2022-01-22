async function fullCurrencyMapFetch() {
    
    var urlCurrenciesBase = 'https://api.coinbase.com/v2/currencies';
    var tempCurrencyMap = new Map();
    return await fetch(urlCurrenciesBase, { method: "GET" })
        .then(response => response.json())
        .then(function (result) {
            
            var obj = result.data;
            var keys =Object.keys(obj)


            for(var i=0; i < obj.length; i++){
                tempCurrencyMap.set((obj[i].id) + " : " +(obj[i].name), (obj[i].id))
   
            };
            return tempCurrencyMap
            })      
        };
        

 async function getFullCurrencyMap(){
    //const delay = async (ms = 1000) => new Promise(resolve => setTimeout(resolve, ms));    
   
    
    const fullCurrencyMap = await fullCurrencyMapFetch();

    
    console.log("getFUllCurrencyMap function hit")
    


    //
    //for (let [key, value] of fullCurrencyMap){
    //    fullCurrencyArray.push(key);   
 
    return fullCurrencyMap;

};


async function createDropDownList1(){

    console.log("creatDropDown function hit")
    var select1 = document.getElementById("dropDownEligible1");

    var fullCurrencyMap = await getFullCurrencyMap();
    var fullCurrencyArray =[];

    console.log(fullCurrencyMap)
    console.log(fullCurrencyMap.size)

    for (let [key, value] of fullCurrencyMap){
          fullCurrencyArray.push(key);  
    }
    
    for(var i = 0; i < fullCurrencyArray.length; i++){
        var opt = fullCurrencyArray[i];

        var ele = document.createElement("option");
        ele.text = opt;
        ele.value = fullCurrencyMap.get(ele.text);
        
        select1.add(ele);
     }
}

async function createDropDownList2(){
  
    console.log("creatDropDown function hit")
    var select1 = document.getElementById("dropDownEligible2");

    var fullCurrencyMap = await getFullCurrencyMap();
    var fullCurrencyArray =[];

    console.log(fullCurrencyMap)
    console.log(fullCurrencyMap.size)

    for (let [key, value] of fullCurrencyMap){
          fullCurrencyArray.push(key);  
    }
    
    for(var i = 0; i < fullCurrencyArray.length; i++){
        var opt = fullCurrencyArray[i];

        var ele = document.createElement("option");
        ele.text = opt;
        ele.value = fullCurrencyMap.get(ele.text);
        
        select1.add(ele);
     }  
}

async function createDropDownList3(){

    console.log("creatDropDown function hit")
    var select1 = document.getElementById("dropDownEligible3");

    var fullCurrencyMap = await getFullCurrencyMap();
    var fullCurrencyArray =[];

    console.log(fullCurrencyMap)
    console.log(fullCurrencyMap.size)

    for (let [key, value] of fullCurrencyMap){
          fullCurrencyArray.push(key);  
    }
    
    for(var i = 0; i < fullCurrencyArray.length; i++){
        var opt = fullCurrencyArray[i];

        var ele = document.createElement("option");
        ele.text = opt;
        ele.value = fullCurrencyMap.get(ele.text);
        
        select1.add(ele);     
    }
}

function RIPIT(){
   
    createDropDownList1();
    createDropDownList2();
    createDropDownList3();
    }

window.fullCurrencyMapFetch = fullCurrencyMapFetch;
window.createDropDownList1 = createDropDownList1;
//window.createDropDownList2 = createDropDownList2;
//window.createDropDownList3 = createDropDownList3;
window.RIPIT = RIPIT;