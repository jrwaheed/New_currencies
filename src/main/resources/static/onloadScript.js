async function fullCurrencyMapFetch() {
    var urlCurrenciesBase = 'https://api.coinbase.com/v2/currencies';
    var fullCurrencyMap = new Map();
    var fullCurrencyArray =[];
    return fetch(urlCurrenciesBase, { method: "GET" })
        .then(response => response.json())
        .then(function (result) {
            
            var obj = result.data;
            var keys =Object.keys(obj)

            var mainKey = Object.keys(result)
            var mainValuestemp = Object.values(result)

            for(var i=0; i < obj.length; i++){
                    fullCurrencyMap.set((obj[i].id) + " - " +(obj[i].name), (obj[i].id))

         
                  
            };
            console.log(fullCurrencyMap)
            return fullCurrencyMap
            })        
        };
        
           

fullCurrencyMapFetch();


function getDropDownList(){
    var select = document.getElementById("selectCurrency");
    var fullCurrencyMap = fullCurrencyMapFetch();
    var fullCurrencyArray =[];

    for (const [key, value] of fullCurrencyMap.entries()){
        fullCurrencyArray.push(fullCurrencyMap[key])
    }
        
    });

    for(var i = 0; i < options.length; i++) {
        var opt = options[i];
        var el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;
        select.appendChild(el);
    }
}

getDropDownList();



window.fullCurrencyMapFetch = fullCurrencyMapFetch;
window.getDropDownList = getDropDownList;