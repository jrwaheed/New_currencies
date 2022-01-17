async function fullCurrencyMapFetch() {
    
    var urlCurrenciesBase = 'https://api.coinbase.com/v2/currencies';
    var fullCurrencyMap = new Map();
    return fetch(urlCurrenciesBase, { method: "GET" })
        .then(response => response.json())
        .then(function (result) {
            
            var obj = result.data;
            var keys =Object.keys(obj)


            for(var i=0; i < obj.length; i++){
                fullCurrencyMap.set((obj[i].id) + " / " +(obj[i].name), (obj[i].id))
   
            };
            return fullCurrencyMap
            })        
        };
        

async function getDropDownList(){
    const delay = async (ms = 1000) => new Promise(resolve => setTimeout(resolve, ms));    

    var fullCurrencyMap =  await fullCurrencyMapFetch();
    var fullCurrencyArray =[];
    var select = document.getElementById("selectCurrency");


    for (let [key, value] of fullCurrencyMap){
       fullCurrencyArray.push(key);   
    }

    

    

    for(var i = 0; i < fullCurrencyArray.length; i++){
        var opt = fullCurrencyArray[i];

        var ele = document.createElement("option");
        ele.text = opt;
        ele.value = fullCurrencyMap.get(ele.text)

        select.add(ele);
    }

    console.log(fullCurrencyArray); 


    return fullCurrencyArray;  
};

getDropDownList();



window.fullCurrencyMapFetch = fullCurrencyMapFetch;
window.getDropDownList = getDropDownList;