


function initiateScatterChart(scatterLabelsList, scatterComboList, objectList){
    const grapharea = document.getElementById('ScatterChart').getContext('2d');

    let scatterChart = new Chart(grapharea, {
        type: 'scatter',
        data: {
            //labels: scatterComboList,
            datasets: [{
                label: scatterComboList[0],
                
                data: objectList,
                pointRadius : 10,
                pointHoverRadius : 15,
                backgroundColor: ['rgba(255, 99, 132, 0.2)'],
                borderColor: ['rgba(255, 99, 132, 1)'],
                borderWidth: 1
            }]
        },
        options: {  
            scales: {
                x: {
                    ticks: {
                        callback: function( value){
                            return new Date(value).toISOString().substring(11,19)
                        } 
                    }
                }
            },
            plugins: {
                tooltip:{
                    model: 'label',
                    callbacks:{
                        label: function(tooltipItem, data){
                         
                            
                            let scatterXY = scatterChart.data.datasets[tooltipItem.datasetIndex].data[tooltipItem.dataIndex];

                            let selectionEpochTime = scatterXY.x;
                            let selectionTime = new Date(selectionEpochTime).toISOString().substring(11,19)

                            return scatterXY.y + ' : ' + selectionTime;
                        }
                    }
                },
                legend:{
                    display: true,
                        }
                    }
                }
    }); 
}



function secondsToTime(epoch) {
    return new Date(epoch).toISOString();
  }

window.initiateScatterChart = initiateScatterChart;
export {initiateScatterChart}

