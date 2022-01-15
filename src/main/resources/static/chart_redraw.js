function refreshChart(){
    let ArbCombos = SQLCalcArbitrage();

    function getFullComboList(ArbCombos){
        let fullComboList = ArbCombos.map(element => element.fullCombo);
        console.log(fullComboList);
        return fullComboList
    };
    
    function getDeltaList(ArbCombos){
        let fullDeltaList = ArbCombos.map(element => element.legOneValue);
        console.log(fullDeltaList);
        return fullDeltaList;
    };
    
    var chartComboList = getFullComboList(ArbCombos);
    var chartDeltaList = getDeltaList(ArbCombos);

    let CurrencyChart  = initiateChart(chartComboList,chartDeltaList);

    CurrencyChart.destroy();

    let CurrencyChart = new Chart(grapharea, {
        type: 'radar',
        data: {
            labels: chartComboList,

            datasets: [{
                label: 'Arbitrage value by Triangle',
                data: chartDeltaList,

                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    CurrencyChart.update();
    CurrencyChart.render();
    console.log("Do something")
}