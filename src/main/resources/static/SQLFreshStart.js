function SQLFreshStart(){
  
    $.ajax({  
        type: 'GET',
        url:'http://localhost:8080/index7',
        
        async: true,
        cache: false,
       
        success: console.log("SQL freshStart executed"),
        error : onerror
    })

    window.location.reload();
}

window.SQLFreshStart = SQLFreshStart;