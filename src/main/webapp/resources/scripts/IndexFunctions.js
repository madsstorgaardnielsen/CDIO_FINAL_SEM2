function getMID(role) { //gets the employee number from user
    $("#header").text("Indtast NR");
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

    Agent.GET('rest/user?userId=' + ID + '&role=' + role, function (data) {
        $("#header").text("Velkommen");
        $("#container").html(
            '<form action="'+ data.role +'.html">' +
            '<div class="boxedText">'+ data.firstName + ' ' + data.lastName +'</div>' +
            '<button class="btn">Videre</button> ' +
            '</form>'
        );
    }, function (data) {
        $("#container").html(data.responseText);
    })
}