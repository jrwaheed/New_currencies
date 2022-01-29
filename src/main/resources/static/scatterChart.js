


function initiateScatterChart(scatterLabelsList, scatterComboList, objectList){
    const grapharea = document.getElementById('ScatterChart').getContext('2d');

    let scatterChart = new Chart(grapharea, {
        type: 'scatter',
        data: {
            datasets: [{
                labels: scatterLabelsList[0],
                data: objectList,
                pointRadius : 10,
                pointHoverRadius : 15,
               
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)'
                  
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)'
                
                ],
                borderWidth: 1
            }]
    },
    options: {  
       
        scales: {
            x: {
                ticks: {
                    callback: function( value){
                        return new Date(value).toISOString().substring(11,19)
                    } },
                    
                
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

