function addRecipeForm() { //gets recipe ID and Name form
    $("#header").text("Opret Recept og dens første komponent");
    $("#container").html(
        '<form action="javascript:addRecipe()">' +
        '<input type="text" placeholder="Recept ID" id="recipeID">' +
        '<input type="text" placeholder="Recept Navn" id="recipeName">' +
        '<button class="btn">Opret Recepten</button>' +
        '</form>'
    );
}


function addRecipe() { //adds the new recipe to backend
    var recipe = {};
    recipe.recipeID = $("#recipeID").val();
    recipe.recipeName = $("#recipeName").val();

//url skal ændres herunder
    Agent.POST("rest/recipe", recipe, function (data) {
        $("#container").html('' +
            '<form action="Farmaceut.html">' +
            '<div class="boxedText">Recept oprettet</div>' +
            '<div class="boxedText">Du kan tilføje flere råvare til recepten inde i redigerens menuen</div>' +
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
        '<th>Recept Navn</th>' +
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
        '<td class= btncont> <button class="viewbtn">Se mere</button></td>' +
        '</tr>'
}

function generateRecipeComponentHtml(recipeComponent) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = recipeID>' + recipeComponent.recipeID + '</td>' +
        '<td class = ingredientId>' + recipeComponent.ingredientID + '</td>' +
        '<td class = ingredientName>' + recipeComponent.ingredientName + '</td>' +
        '<td class = nonNetto>' + recipeComponent.nonNetto + ' kg</td>' +
        '<td class = tolerance>' + recipeComponent.tolerance + ' %</td>' +
        '<td class = editbutton> <button class="editbtn">Rediger Komponent</button></td>' +
        '<td class = deletebutton> <button class="delbtn">Slet Komponent</button></td>' +
        '</tr>'
}

function getRecipeComponent(recipeId) {
    $("#header").text("Komponent Oversigt");
    $("#container").attr('data-recipeId', recipeId);
    $("#container").html(
        '<table> <thead> <tr>' +
        '<th>Recept ID</th>' +
        '<th>Råvare ID</th>' +
        '<th>Råvare navn</th>' +
        '<th>Nominel Netto</th>' +
        '<th>Tolerance</th>' +
        '<th colspan="1"></th>' +
        '<th colspan="1"></th>' +
        '</tr> </thead> ' +
        '<tbody id="tablebody"></tbody> ' +
        '</table>' +
        '<div class="infocontainer" id="inputID">' +
        '<button class="confirmbtn" id="addbtn">Tilføj ny</button>' +
        '</div>'
    );

    Agent.GET('rest/recipecomponent/' + recipeId + '/', function (data) {
        $.each(data, function () {
            $("#tablebody").append(generateRecipeComponentHtml(this));
        });
        listener();
        listeneredit();
        listenersave();
        listenerdelete();
        componentlistenerAdd();
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

function listeneredit() {
    $("#container").on('click', '.editbtn', function () {
        var row = $(this).closest('tr');
        //var recipeID = row.find(".recipeID").text();
        //var ingredientId = row.find(".ingredientId").text();
        //var ingredientName = row.find(".ingredientName").text();
        var nonNetto = row.find(".nonNetto").text();
        var tolerance = row.find(".tolerance").text();

        //row.find(".recipeID").html('<input type="text" placeholder="' + recipeID + '" id="editrecipeID" data-orig="'+ recipeID +'">');
        //row.find(".ingredientId").html('<input type="text" placeholder="' + ingredientId + '" id="editingredientId" data-orig="'+ ingredientId +'">');
        //row.find(".ingredientName").html('<input type="text" placeholder="' + ingredientName + '" id="editingredientName" data-orig="'+ ingredientName +'">');
        row.find(".nonNetto").html('<input type="text" placeholder="' + nonNetto + '" id="editnonNetto" data-orig="'+ nonNetto +'">');
        row.find(".tolerance").html('<input type="text" placeholder="' + tolerance + '" id="edittolerance" data-orig="'+ tolerance +'">');
        row.find(".editbutton").empty();
        row.find(".editbutton").html('<button class="savebtn">Gem</button>');
    })
}

function listenersave() {
    $("#container").on('click', '.savebtn', function () {
        var row = $(this).closest('tr');
        var recipeID = row.find(".recipeID").text();
        var ingredientId = row.find(".ingredientId").text();
        var ingredientName = row.find(".ingredientName").text();
        var nonNetto = row.find("#editnonNetto").val();
        var tolerance = row.find("#edittolerance").val();

        var orignonNetto = row.find("#editnonNetto").attr('placeholder');
        var origtolerance = row.find("#edittolerance").attr('placeholder');

        var params = "?recipeID=" + recipeID;
            params = params + "&ingredientId=" + ingredientId;
            params = params + "&ingredientName=" + ingredientName;

        if (nonNetto !== "")
            params = params + "&nonNetto=" + nonNetto;
        else
            params = params + "&nonNetto=" + orignonNetto;

        if (tolerance !== "")
            params = params + "&tolerance=" + tolerance;
        else
            params = params + "&tolerance=" + origtolerance;

        Agent.PUT("rest/recipecomponent" + params, null, function (data) {
            row.replaceWith(generateRecipeComponentHtml(data));
        }, function (data) {
            window.alert("Ændringer ikke gemt: " + $(data.responseText).find("u").first().text());
            console.log(data)
        })

    })
}

function listenerdelete() {
    $("#container").on('click', '.delbtn', function () {
        var row = $(this).closest('tr');

        var recipeID = row.find(".recipeID").text();
        var ingredientId = row.find(".ingredientId").text();

        var params = "?recipeID=" + recipeID;
        params = params + "&ingredientId=" + ingredientId;

        Agent.DELETE("rest/recipecomponent" + params, null, function (data) {
            getRecipeComponent(recipeID)
        }, function (data) {
            window.alert("Data blev ikke slettet: " + $(data.responseText).find("u").first().text());
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


function componentlistenerAdd() {//shows line to add new batch by recipe id
    $("#container").on('click', "#addbtn", function () {
        $("#inputID").html('' +
            '<form action="javascript:saveComponent()">' +
            '<input id="ingredientIdInput" type="text" placeholder="Ingredient ID" >' +
            '<input id="nonNettoInput" type="text" placeholder="Nominel Netto" >' +
            '<input id="toleranceInput" type="text" placeholder="Tolerance" >' +
            '<br>' +
            '<button id="finishcompbtn" class="btn" type="submit" >Udfør</button>' +
            '</form>'
        )
    })
}

function saveComponent() {
        var recipecomponent = {};

        recipecomponent.recipeID =  $("#container").attr('data-recipeId');
        recipecomponent.ingredientID = $("#ingredientIdInput").val();
        recipecomponent.nonNetto = $("#nonNettoInput").val();
        recipecomponent.tolerance = $("#toleranceInput").val();


        Agent.POST("rest/recipecomponent", recipecomponent, function () {
            getRecipeComponent(recipecomponent.recipeID)
        }, function (data) {
            $("#error").remove();
            console.log(data);
            $("#container").append('' +
                '<div class="errorcont"><div class="boxedText" id="error">' +
                'Component ikke tilføjet: ' + $(data.responseText).find("u").first().text() +
                '</div></div>'
            );
        })
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

function addIngredientform(){ // Opret en råvare form
    $("#header").text("Råvare administration");
    $("#container").html(
        '<form action="javascript:addIngredient()">' +
        '<input type="text" placeholder="Råvare ID" id="ingredientID">' +
        '<input type="text" placeholder="Råvare Navn" id="ingredientName">' +
        '</select> <br>' +
        '<button class="btn">Gem</button>' +
        '</form>'
    );

}
function addIngredient() { //adds the new ingredient to backend
    var ingredient = {};
    ingredient.ingredientID = $("#ingredientID").val();
    ingredient.ingredientName = $("#ingredientName").val();

    Agent.POST("rest/ingredient", ingredient, function () {
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
        '<th>Råvare ID</th>' +
        '<th>Råvare Navn</th>' +

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


