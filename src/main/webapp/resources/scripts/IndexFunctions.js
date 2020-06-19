function getMID(role) { //gets the employee number from user
    $("#header").text("Indtast bruger ID");
    $("#container").html(
        '<form action="javascript:displayName()">' +
        '<input type="text" placeholder="Nr." id="MID" data-role="' + role + '">' +
        '<button class="btn">OK</button>' +
        '</form>'
    );

    $("#optionsbox").html('' +
        '<table class="optionstable"><thead><tr>' +
        '<th>UserID</th>' +
        '<th>User role</th>' +
        '</tr></thead>' +
        '<tbody id="tablebody"></tbody>' +
        '</table>'
    );

    Agent.GET("rest/user", function (data) {
        $.each(data, function () {
            if (this.role === role) {
                $("#tablebody").append('' +
                    '<tr>' +
                    '<td>' + this.userId + '</td>' +
                    '<td>' + this.role + '</td>' +
                    '</tr>'
                )
            }
        }, function (data) {
            $("#optionsbox").html('' +
                '<div class="boxedText" id="error">'
                + $(data.responseText).find("u").first().text() +
                '</div>'
            )
        });
    });
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
        $("#error").remove();
        console.log(data);
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            'Fejl: '+ $(data.responseText).find("u").first().text() +
            '</div></div>'
        );
    })
}

function next(ID) {
    localStorage.setItem('ID', ID);
}