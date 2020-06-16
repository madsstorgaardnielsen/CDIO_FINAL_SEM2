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
//Tilføj en råvare funktion.
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
    recipe.recipeName = $("#recipeName").val();
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

function getRecipes() { //gets existing Recipes from backend
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

    Agent.GET("rest/recipe", function (data) {
        $.each(data, function () {
            $("#tablebody").append(generateRecipeHtml(this));
        });
        getRecipeIDFromRow();
    }, function (data) {
        $("#container").html($(data.responseText).find("u").first().text());
    });
}


function generateRecipeHtml(recipe) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = recipeID>' + recipe.recipeID + '</td>' +
        '<td class= recipeName>' + recipe.recipeName + '</td>' +
        '<td class= btncont> <button class="viewbtn">View components</button></td>' +
        '</tr>'
}

function generateRecipeComponentHtml(recipeComponent) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = recipeID>' + recipeComponent.recipeID + '</td>' +
        '<td class = ingredientId>' + recipeComponent.ingredientID + '</td>' +
        '<td class = nonNetto>' + recipeComponent.nonNetto + '</td>' +
        '<td class = tolerance>' + recipeComponent.tolerance + '</td>' +
        '<td class = btncont> <button class="editbtn">Edit component</button></td>' +
        '</tr>'
}

function getRecipeComponent(recipeId) {
    $("#header").text("Komponent Oversigt");
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

    Agent.GET('rest/recipecomponent/' + recipeId + '/', function (data) {
        $.each(data, function () {
            $("#tablebody").append(generateRecipeComponentHtml(this));
        });
        listener;
        listeneredit;
        listenersave;
    }, function (data) {
        $("#container").html($(data.responseText).find("u").first().text());
    });
}


function getRecipeIDFromRow() {
    $("#container").on('click', '.viewbtn', function () {
        var row = $(this).closest('tr');
        var recipeID = row.find(".recipeID").text();

        getRecipeComponent(recipeID);
    })
}

function listeneredit(row) {
    $(row).on('click', '.editbtn', function () {
        var row = $(this).closest('tr');
        var recipeID = row.find(".RecipeID").text();
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


//functions for ingredients and htmlgeneration below

function addIngredientform(){ // Opret en ingredients form
    $("#header").text("Råvare administration");
    $("#container").html(
        '<form action="javascript:addIngredient()">' +
        '<input type="text" placeholder="Ingredient ID" id="ingredientID">' +
        '<input type="text" placeholder="Ingredient Name" id="ingredientName">' +
        '</select> <br>' +
        '<button class="btn">Bekræft ID og navn</button>' +
        '</form>'
    );

}
function addIngredient() { //adds the new ingredient to backend
    var ingredient = {};
    ingredient.ingredientID = $("#ingredientID").val();
    ingredient.ingredientName = $("#ingredientName").val();

    Agent.POST("rest/ingredient", ingredient, function (data) {
        $("#container").html('' +
            '<form action="Farmaceut.html">' +
            '<div class="boxedText">Råvare oprettet</div>' +
            '<button class="btn">Videre</button>' +
            '</form>'
        )
    }, function (data) {
        $("#error").remove();
        console.log(data);
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            'Råvare ikke tilføjet: '+ $(data.responseText).find("u").first().text() +
            '</div></div>'
        );
    })
}

function getIngredient() { //gets existing ingredients from backend
    $("#header").text("Råvare oversigt");
    $("#container").html(
        '<table> <thead> <tr>' +
        '<th>Ingredient ID</th>' +
        '<th>Ingredient Name</th>' +

        '</tr> </thead> ' +
        '<tbody id="tablebody"></tbody> ' +
        '</table>'
    );
    var row;
    Agent.GET("rest/ingredient", function (data) {
        $.each(data, function () {
            row = $("#tablebody").append(generateIngredientHtml(this));
        });
       }, function (data) {
        $("#container").html($(data.responseText).find("u").first().text());
    });
}

function generateIngredientHtml(ingredients) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = ingredientID>' + ingredients.ingredientID + '</td>' +
        '<td class = ingredientName>' + ingredients.ingredientName + '</td>' +
        '</tr>'
}


