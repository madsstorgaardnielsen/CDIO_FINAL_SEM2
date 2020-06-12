function addRecipeForm() { //gets recipe
    $("#header").text("Indtast Recipe ID");
    $("#container").html(
        '<form action="javascript:addRecipe()">' +
        '<input type="text" placeholder="Recipe ID" id="RecipeID">' +
        '<input type="text" placeholder="Recipe Name" id="recipeName">' +
        '<input type="text" placeholder="ingrediantID" id="ingrediantID">' +
        '<input type="text" placeholder="Recipe Name" id="nonNetto">' +
        '<input type="text" placeholder="Recipe ID" id="tolerance">' +
        '</select> <br>' +
        '<button class="btn">Opret</button>' +
        '</form>'
    );
}

function addRecipe() { //adds the new recipe to backend
    var recipe = {};
    //recipe.userId = 0;
    recipe.RecipeID = $("#recipeID").val();
    recipe.recipeName = $("#recipeName").val();
    recipe.ingrediantID = $("#ingrtediantID").val();
    recipe.nonNetto = $("#nonNetto").val();
    recipe.nonNetto = $("#tolerance").val();
    //skal måske fjernes
    recipe.active = true;
//url skal ændres herunder
    Agent.POST("rest/user", recipe, function (data) {
        $("#container").html('' +
            '<form action="farmaceut.html">' +
            '<div class="boxedText">Recept oprettet</div>' +
            '<button class="btn">Videre</button>' +
            '</form>'
        )
    }, function (data) {
        $("#error").remove();
        console.log(data);
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            'Recept ikke tilføjet: '+ $(data.responseText).find("u").first().text() +
            '</div></div>'
        );
    })
}

function getRecipe() { //gets existing Recipes from backend
    $("#header").text("Recept oversigt");
    $("#container").html(
        '<table> <thead> <tr>' +
        '<th>Recept ID</th>' +
        '<th>Recept Navn</th>' +
        '<th>ingredient ID</th>' +
        '<th>nonNetto</th>' +
        '<th>Tolerance</th>' +
        '<th colspan="2"></th>' +
        '</tr> </thead> ' +
        '<tbody id="tablebody"></tbody> ' +
        '</table>'
    );
    var row;
    Agent.GET("rest/user", function (data) {
        $.each(data, function () {
            row = $("#tablebody").append(generateRecipeHtml(this));
        });
        listener(row);
        listeneredit(row);
        listenersave(row);
    }, function (data) {
        $("#container").html($(data.responseText).find("u").first().text());
    });
}


function generateRecipeHtml(recipe) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = recipeID>' + recipe.recipeID + '</td>' +
        '<td class= name>' + recipe.name + '</td>' +
        '<td class= ingrediantID>' + recipe.ingrediantID + '</td>' +
        '<td class= nonNetto>' + recipe.nonNetto + '</td>' +
        '<td class= tolerance>' + recipe.tolerance + '</td>' +
        '<td class= active>' + booleanToText(recipe.active) + '</td>' +
        '<td class= editbutton> <button class="editbtn">Ændre</button></td>' +
        '<td class= toglebutton> <button class="toglebtn">'+ booleanToBtn(recipe.active) +'</button> </td>' +
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