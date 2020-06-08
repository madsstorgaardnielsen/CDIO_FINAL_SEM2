function addUserForm() { //gets user credentials for new user from admin user
    $("#header").text("Indtast bruger oplysninger");
    $("#container").html(
        '<form action="javascript: addUser">' +
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
    //TODO: kode der tilføjer bruger til backend
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

    var user = {};
    user.userId = 12;
    user.role = "Admin";
    user.firstName = "Mads";
    user.lastName = "Langer";
    user.initials = "ML";
    user.active = true;
    $("#tablebody").append(generateUserHtml(user));

    user.userId = 14;
    user.role = "Admin";
    user.firstName = "Magnus";
    user.lastName = "Milang";
    user.initials = "MM";
    user.active = false;
    $("#tablebody").append(generateUserHtml(user));


    //TODO: kode der henter brugere fra backend
    //TODO: kode der appender brugere til container
}


function generateUserHtml(user) { //generates html to show in user table
    return '<tr> ' +
        '<td class = userId>' + user.userId + '</td>' +
        '<td class=>' + user.role + '</td>' +
        '<td class=>' + user.firstName + '</td>' +
        '<td class=>' + user.lastName + '</td>' +
        '<td class=>' + user.initials + '</td>' +
        '<td class=>' + booleanToText(user.active) + '</td>' +
        '<td class=> <button class="editbtn">Ændre</button></td>' +
        '<td class=> <button class="toglebtn">'+ booleanToBtn(user.active) +'</button> </td>' +
        '</tr>'
}

//TODO: eventlistener på editbtn

//TODO: eventlistener på toglebtn

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