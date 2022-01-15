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
            })

        
    }    
;
        
           

fullCurrencyMapFetch();


/*
for(var i=0; i < obj.length; i++){
    for(var element in obj[i]){
        console.log(obj[i][element])

          console.log(obj[0].id);
                    console.log(obj[0].name);
*/