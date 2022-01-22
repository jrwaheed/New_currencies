import {bigRun} from '/src/main/resources/static/script.js';
import {createScatterPlotButtons} from '/src/main/resources/static/SQLGetScatterPlotData.js';

function DSinitialRun(){
    bigRun();
    setTimeout(createScatterPlotButtons,3000);
    //createScatterPlotButtons();

}

window.DSinitialRun = DSinitialRun;