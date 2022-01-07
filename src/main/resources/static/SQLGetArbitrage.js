function SQLGetArbitrage(){
    var ArbCombos ="";

    $.ajax({  
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'GET',
        url:'http://localhost:8080/index4',
        
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        
        success: function(response){
        ArbCombos = response;
        }
    });

    console.log(ArbCombos)
    

      
}