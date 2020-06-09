function addUserForm() { //gets user credentials for new user from admin user
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

function addUser() { //adds the new user to backend
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
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            'Bruger ikke tilføjet: status code '+ data.status +
            '</div></div>'
        )
    })
}

function getUsers() { //gets existing users from backend
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

    Agent.GET("rest/user", function (data) {
        $.each(data, function () {
            $("#tablebody").append(generateUserHtml(this));
        });
        listener();
    }, function (data) {
        $("#container").html(data.responseText);
    })
}


function generateUserHtml(user) { //generates html to show in user table
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

//TODO: eventlistener på editbtn
function listener() {
    $("#tablebody").on('click', '.toglebtn', function () {
        var row = $(this).closest('tr');
        var active = invertTextToBoolean(row.find(".active").text());
        var ID = row.find(".userId").text();

        Agent.PUT("rest/user?userId=" + ID + "&active=" + active, null, function (data) {
            row.replaceWith(generateUserHtml(data))
        }, function (data) {
            window.alert(data.reason);
        })
    });
}

//TODO: måde at opdatere data når der er lavet ændringer (enten alt data eller den ændrede)



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

function toIndex() {
    window.location = "localhost:8080";
}