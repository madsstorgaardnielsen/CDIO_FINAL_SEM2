function addRecipeForm() { //gets recipe ID and Name form
    $("#header").text("Opret en recept");
    $("#container").html(
        '<form action="javascript:generateIngredient()">' +
        '<input type="text" placeholder="Recipe ID" id="RecipeID">' +
        '<input type="text" placeholder="Recipe Name" id="recipeName">' +
        '</select> <br>' +
        '<button class="btn">bekræft ID og navn</button>' +
        '</form>'
    );
}
//Titilføj en råvare funktion.
function generateIngredient() {
    $("#header").text("Opret en recept");
    $("#container").html(
        '<form action="javascript:generateRow()">' +

     '<tr>' +
        '<td class="table">' +
        '<td> <input  type="text" placeholder="råvare ID"> </td>' +
        '<td> <input  type="text" placeholder="non Netto vægt i gram"> </td>' +
        '<td> <input  type="text" placeholder="Tolerance"> </td>' +
        '<td> <button> class="btn" Bekræft </button> </td>' +
        '</tr>' +
        '<button class="btn">Tilføj råvare</button>'+
        '</form>');


}

function addRecipe() { //adds the new recipe to backend
    var recipe = {};
    //recipe.userId = 0;
    recipe.RecipeID = $("#recipeID").val();
    //recipe.recipeName = $("#recipeName").val();
    recipe.ingredientID = $("#ingredientID").val();
    recipe.nonNetto = $("#nonNetto").val();
    recipe.ingredientID = $("#tolerance").val();

    //skal måske fjernes
    recipe.active = true;
//url skal ændres herunder
    Agent.POST("rest/recipe", recipe, function (data) {
        $("#container").html('' +
            '<form action="Farmaceut.html">' +
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
        '<th>Recept Name</th>' +
        '<th colspan="1"></th>' +
        '</tr> </thead> ' +
        '<tbody id="tablebody"></tbody> ' +
        '</table>'
    );
    var row;
    Agent.GET("rest/recipe", function (data) {
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
        '<td class= viewbtn> <button class="viewbtn">View commponents</button></td>' +
        '</tr>'
}

function getRecipeComponent(recipeID) {
    function getUsers() { //gets existing users from backend
        $("#header").text("Brugeroversigt");
        $("#container").html(
            '<table> <thead> <tr>' +
            '<th>RecipeID</th>' +
            '<th>ingredientID</th>' +
            '<th>nonNetto</th>' +
            '<th>tolerance</th>' +
            '<th colspan="1"></th>' +
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
}

function listeneredit(row) {
    $(row).on('click', '.editbtn', function () {
        var row = $(this).closest('tr');
        var recipeID = row.find(".firstName").text();
        var recipeName = row.find(".recipeName").text();


        row.find(".recipeID").html('<input type="text" placeholder="' + recipeID + '" id="editrecipeID" data-orig="'+ recipeID +'">');
        row.find(".recipeName").html('<input type="text" placeholder="' + recipeName + '" id="editrecipeName" data-orig="'+ recipeName +'">');


        row.find(".editbutton").html('<button class="savebtn">Gem</button>');
    })
}

function listenersave(row) {
    $(row).on('click', '.savebtn', function () {
        var row = $(this).closest('tr');
        var recipeID = row.find(".userId").text();
        var recipeName = row.find("#editrecipeID").val();


        var origrecipeName = row.find("#editrecipeName").attr('data-orig');
        var origingredientID = row.find("#editingredientID").attr('data-orig');
        var orignonNetto = row.find("#editnonNetto").attr('data-orig');
        var origtolerance = row.find("#edittolerance").attr('data-orig');

        var params = "?userId=" + recipeID;
        

        if (recipeName !== "" && recipeName !== origrecipeName)
            params = params + "&recipeName=" + recipeName;

        if (ingredientID !== "" && ingredientID !== origingredientID)
            params= params + "&ingredientID=" + ingredientID;

        if (nonNetto !== "" && nonNetto !== orignonNetto)
            params = params + "&nonNetto=" + nonNetto;

        if (tolerance !== origtolerance)
            params = params + "&tolerance=" + tolerance;

        Agent.PUT("rest/user" + params, null, function (data) {
            row.replaceWith(generateRecipeHtml(data));
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
        var ID = row.find(".recipeID").text();

        Agent.PUT("rest/recipe?recipeID=" + ID + "&active=" + active, null, function (data) {
            row.replaceWith(generateRecipeHtml(data))
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