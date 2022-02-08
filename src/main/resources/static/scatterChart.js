
   


function startScatterChart(scatterLabelsList, scatterComboList, objectList){
    //var canvas = document.getElementById('ScatterChart').getContext('2d');
    
    

    if( window.scatterChart == null){
        initiateChart(scatterLabelsList, scatterComboList, objectList);
    } else {
        destroy();
        initiateChart(scatterLabelsList, scatterComboList, objectList);
    }
}

function renderScatter (){
    scatterChart.render();
}


function destroy(){
    scatterChart.destroy();
}

function initiateChart(scatterLabelsList, scatterComboList, objectList){

    // data block
    let data ={
        datasets: [{
            label: scatterComboList[0], 
            data: objectList,
            pointRadius : 5,
            pointHoverRadius : 7,
            backgroundColor: ['#e4a576'],
            borderColor: ['rgba(255, 99, 132, 1)'],
            borderWidth: 1
        }]
        
    }


    //configuration block
    let configuration = {  
    type: 'scatter',
    data,
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
                yAlign:'bottom',
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
        }


   //initialize block
   window.scatterChart = new Chart(
    document.getElementById('ScatterChart').getContext('2d'), 
    configuration
);
};






function secondsToTime(epoch) {
    return new Date(epoch).toISOString();
  }

window.startScatterChart = startScatterChart;
window.renderScatter = renderScatter;
export {startScatterChart};
