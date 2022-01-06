async function fullCurrencyMapFetch() {
    var urlCurrenciesBase = 'https://api.coinbase.com/v2/currencies';
    var fullCurrencyMap = new Map();
    return fetch(urlCurrenciesBase, { method: "GET" })
        .then(response => response.json())
        .then(function (result) {
            var obj = result.data;
            var keys = Object.keys(obj);
            for (var val in keys) {
                fullCurrencyMap .set(keys["id"], obj["name"]]);
            }
            console.log(fullCurrencyMap)
        });
};

fullCurrencyMapFetch();