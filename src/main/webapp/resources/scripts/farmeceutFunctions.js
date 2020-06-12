function addRecipeForm() { //gets user credentials for new user from admin user
    $("#header").text("Indtast bruger oplysninger");
    $("#container").html(
        '<form action="javascript:addUser()">' +
        '<input type="text" placeholder="Fornavn" id="firstName">' +
        '<input type="text" placeholder="Efternavn" id="lastName">' +
        '<input type="text" placeholder="Initialer" id="ini">' +
        '<select id="roller" required>' +
        '<option value="" disabled selected hidden>Rolle</option> ' +
        '<option value="Admin">Admin</option>' +
        '<option value="Farmaceut">Farmaceut</option>' +
        '<option value="Produktionsleder">Produktionsleder</option>' +
        '<option value="Laborant">Laborant</option> ' +
        '</select> <br>' +
        '<button class="btn">Opret</button>' +
        '</form>'
    );
}

function addRecipe() { //adds the new user to backend
    var user = {};
    user.userId = 0;
    user.firstName = $("#firstName").val();
    user.lastName = $("#lastName").val();
    user.initials = $("#ini").val();
    user.role = $("#roller").val();
    user.active = true;

    Agent.POST("rest/user", user, function (data) {
        $("#container").html('' +
            '<form action="Admin.html">' +
            '<div class="boxedText">Bruger oprettet</div>' +
            '<button class="btn">Videre</button>' +
            '</form>'
        )
    }, function (data) {
        $("#error").remove();
        console.log(data);
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            'Bruger ikke tilføjet: '+ $(data.responseText).find("u").first().text() +
            '</div></div>'
        );
    })
}

function getRecipe() { //gets existing users from backend
    $("#header").text("Brugeroversigt");
    $("#container").html(
        '<table> <thead> <tr>' +
        '<th>ID</th>' +
        '<th>Rolle</th>' +
        '<th>Fornavn</th>' +
        '<th>Efternavn</th>' +
        '<th>Initialer</th>' +
        '<th>Aktiv</th>' +
        '<th colspan="2"></th>' +
        '</tr> </thead> ' +
        '<tbody id="tablebody"></tbody> ' +
        '</table>'
    );
    var row;
    Agent.GET("rest/user", function (data) {
        $.each(data, function () {
            row = $("#tablebody").append(generateUserHtml(this));
        });
        listener(row);
        listeneredit(row);
        listenersave(row);
    }, function (data) {
        $("#container").html($(data.responseText).find("u").first().text());
    });
}


function generateReceipeHtml(user) { //generates html to show in user table
    return '<tr> ' +
        '<td class = userId>' + user.userId + '</td>' +
        '<td class= role>' + user.role + '</td>' +
        '<td class= firstName>' + user.firstName + '</td>' +
        '<td class= lastName>' + user.lastName + '</td>' +
        '<td class= initials>' + user.initials + '</td>' +
        '<td class= active>' + booleanToText(user.active) + '</td>' +
        '<td class= editbutton> <button class="editbtn">Ændre</button></td>' +
        '<td class= toglebutton> <button class="toglebtn">'+ booleanToBtn(user.active) +'</button> </td>' +
        '</tr>'
}

function listeneredit(row) {
    $(row).on('click', '.editbtn', function () {
        var row = $(this).closest('tr');
        var firstName = row.find(".firstName").text();
        var lastName = row.find(".lastName").text();
        var initials = row.find(".initials").text();
        var role = row.find(".role").text();

        row.find(".firstName").html('<input type="text" placeholder="' + firstName + '" id="editfirstName" data-orig="'+ firstName +'">');
        row.find(".lastName").html('<input type="text" placeholder="' + lastName + '" id="editlastName" data-orig="'+ lastName +'">');
        row.find(".initials").html('<input type="text" placeholder="' + initials + '" id="editinitials" data-orig="'+ initials +'">');
        row.find(".role").html('' +
            '<select id="editrole" data-orig="'+ role +'">' +
            '<option value="'+ role +'" selected hidden>'+ role +'</option>' +
            '<option value="Admin">Admin</option>' +
            '<option value="Farmaceut">Farmaceut</option>' +
            '<option value="Produktionsleder">Produktionsleder</option>' +
            '<option value="Laborant">Laborant</option>' +
            '</select>'
        );
        row.find(".editbutton").html('<button class="savebtn">Gem</button>');
    })
}

function listenersave(row) {
    $(row).on('click', '.savebtn', function () {
        var row = $(this).closest('tr');
        var userId = row.find(".userId").text();
        var firstName = row.find("#editfirstName").val();
        var lastName = row.find("#editlastName").val();
        var initials = row.find("#editinitials").val();
        var role = row.find("#editrole").val();

        var origfirstName = row.find("#editfirstName").attr('data-orig');
        var origlastName = row.find("#editlastName").attr('data-orig');
        var originitials = row.find("#editinitials").attr('data-orig');
        var origrole = row.find("#editrole").attr('data-orig');

        var params = "?userId=" + userId;

        if (firstName !== "" && firstName !== origfirstName)
            params = params + "&firstName=" + firstName;

        if (lastName !== "" && lastName !== origlastName)
            params= params + "&lastName=" + lastName;

        if (initials !== "" && initials !== originitials)
            params = params + "&initials=" + initials;

        if (role !== origrole)
            params = params + "&role=" + role;

        Agent.PUT("rest/user" + params, null, function (data) {
            row.replaceWith(generateUserHtml(data));
        }, function (data) {
            window.alert("Ændringer ikke gemt: " + $(data.responseText).find("u").first().text());
            console.log(data)
        })

    })
}



function listener(row) {
    $(row).on('click', '.toglebtn', function () {
        var row = $(this).closest('tr');
        var active = invertTextToBoolean(row.find(".active").text());
        var ID = row.find(".userId").text();

        Agent.PUT("rest/user?userId=" + ID + "&active=" + active, null, function (data) {
            row.replaceWith(generateUserHtml(data))
        }, function (data) {
            window.alert("Kunne ikke ændre status: " + $(data.responseText).find("u").first().text());
            console.log(data)
        })
    });
}

function booleanToBtn(active) { //generates text according to users active state
    if (active)
        return "deaktiver";
    else
        return "aktiver";
}
function booleanToText(active) { //generates text according to users active state
    if (active)
        return "ja";
    else
        return "nej";
}

function invertTextToBoolean(active) {
    if (active === "ja")
        return false;
    else
        return true;
}