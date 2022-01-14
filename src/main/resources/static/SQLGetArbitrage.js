

function SQLCalcArbitrage(){
    var ArbCombos = [];

    $.ajax({  
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'GET',
        url:'http://localhost:8080/index4',
        
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function(response){
        ArbCombos = response;
        }
    });
    for(var i = 0; i < ArbCombos.length; i++){
        console.log(ArbCombos[i])
    };
    return ArbCombos;

}
 
export {SQLCalcArbitrage}


