


function initiateScatterChart(scatterLabelsList, scatterComboList, objectList){
    const grapharea = document.getElementById('ScatterChart').getContext('2d');

    let scatterChart = new Chart(grapharea, {
        type: 'scatter',
        data: {
            datasets: [{
                labels: scatterLabelsList,
                data: objectList,
                //data: [{x: 77000, y: 2.765}],
               
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)'
                  
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)'
                
                ],
                borderWidth: 1
            }]
    },
   
    });
    return scatterChart
}
//window.initiateScatterChart = initiateScatterChart;


function buildScatterChart(){
   
}

window.initiateScatterChart = initiateScatterChart;
export {initiateScatterChart}

