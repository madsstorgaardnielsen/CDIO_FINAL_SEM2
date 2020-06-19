function getMID(role) { //gets the employee number from user
    $("#header").text("Indtast bruger ID");
    $("#container").html(
        '<form action="javascript:displayName()">' +
        '<input type="text" placeholder="Nr." id="MID" data-role="'+ role +'">' +
        '<button class="btn">OK</button>' +
        '</form>'
    );
}

function displayName() { //gets the information about the user from the backend
    var ID = $("#MID").val();
    var role = $("#MID").attr("data-role");

    Agent.GET('rest/user/' + ID + '/' + role + "/", function (data) {
        $("#header").text("Logget ind som: ");
        next(ID)
        $("#container").html(
            '<form action="'+ role +'.html">' +
            '<div class="boxedText">'+role+ ', '+ data.firstName + ' ' + data.lastName +'</div>' +
            '<button class="btn">Videre</button> ' +
            '</form>'
        );
    }, function (data) {
        $("#container").html(data.responseText);
    })
}

function next(ID) {
    localStorage.setItem('ID', ID);
}