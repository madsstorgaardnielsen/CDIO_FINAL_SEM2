function addRecipeForm() { //gets recipe ID and Name form
    $("#header").text("Opret Recept");
    $("#container").html(
        '<form action="javascript:addRecipe()">' +
        '<input type="text" placeholder="Recept ID" id="recipeID">' +
        '<input type="text" placeholder="Recept Navn" id="recipeName">' +
        '<button class="btn">Opret Recept</button>' +
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
            '<div class="boxedText">Du kan tilføje komponenter i Vis/Rediger under farmaceut menuen</div>' +
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
        '<td class= btncont> <button class="viewbtn">Tilføj/fjern komponenter</button></td>' +
        '</tr>'
}

function generateRecipeComponentHtml(recipeComponent) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = recipeID>' + recipeComponent.recipeID + '</td>' +
        '<td class = ingredientId>' + recipeComponent.ingredientID + '</td>' +
        '<td class = ingredientName>' + recipeComponent.ingredientName + '</td>' +
        '<td class = nonNetto>' + recipeComponent.nonNetto + ' kg</td>' +
        '<td class = tolerance>' + recipeComponent.tolerance + ' %</td>' +
        '<td class = editbutton> <button class="editbtn">Rediger</button></td>' +
        '<td class = deletebutton> <button class="delbtn">Slet</button></td>' +
        '</tr>'
}

function getRecipeComponent(recipeId) {
    $("#options").remove();
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
        '<button class="confirmbtn" id="addbtn">Tilføj komponent</button>' +
        '</div>'
    );

    Agent.GET('rest/recipecomponent/' + recipeId + '/', function (data) {
        $.each(data, function () {
            $("#tablebody").append(generateRecipeComponentHtml(this));
        });

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
        var nonNetto = row.find(".nonNetto").text();
        var tolerance = row.find(".tolerance").text();

        row.find(".nonNetto").html('<input type="number" placeholder="' + nonNetto + '" id="editnonNetto" data-orig="'+ nonNetto +'" step="0.0001" min="0">');
        row.find(".tolerance").html('<input type="number" placeholder="' + tolerance + '" id="edittolerance" data-orig="'+ tolerance +'" step="0.0001" min="0">');
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
        Agent.GET("rest/ingredient", function (data) {
            $("body").append('' +
                '<div id="options">' +
                '<table class="optionstable"><thead><tr>' +
                '<th>Råvare ID</th>' +
                '<th>Råvare Navn</th>' +
                '</tr></thead>' +
                '<tbody id="tablebod"></tbody>' +
                '</table>' +
                '</div>'
            );

            $.each(data, function () {
                $("#tablebod").append('' +
                    '<tr>' +
                    '<td>'+ this.ingredientID +'</td>' +
                    '<td>'+ this.ingredientName +'</td>'+
                    '</tr>'
                )
            });

        }, function (data) {
            $("body").append('<div id="options"></div>');
            $("#options").html('' +
                '<div class="boxedText" id="error">'
                + $(data.responseText).find("u").first().text() +
                '</div>'
            )
        });

        $("#inputID").html('' +
            '<form action="javascript:saveComponent()">' +
            '<input id="ingredientIdInput" type="number" placeholder="Råvare ID" >' +
            '<input id="nonNettoInput" type="number" placeholder="Nominel Netto" step="0.0001" min="0">' +
            '<input id="toleranceInput" type="number" placeholder="Tolerance" step="0.0001" min="0">' +
            '<br>' +
            '<button id="finishcompbtn" class="btn" type="submit" >Tilføj</button>' +
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


        Agent.POST("rest/recipecomponent", recipecomponent , function () {
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
        '<input type="number" placeholder="Råvare ID" id="ingredientID">' +
        '<input type="text" placeholder="Råvare Navn" id="ingredientName">' +
        '</select> <br>' +
        '<button class="btn">Opret råvare</button>' +
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
            $(data.responseText).find("u").first().text() +
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
        '<th colspan="1"></th>' +
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
    }

    );

}

function generateIngredientHtml(ingredients) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = ingredientID>' + ingredients.ingredientID + '</td>' +
        '<td class = ingredientName>' + ingredients.ingredientName + '</td>' +
        '<td class = editbutton> <button class="editbtnIngredient">Rediger</button></td>' +
        '</tr>'
}


function listenereditIngredient() {
    $("#container").on('click', '.editbtnIngredient', function () {
        var row = $(this).closest('tr');
        var ingredientName = row.find(".ingredientName").text();
        row.find(".ingredientName").html('<input type="text" placeholder="' + ingredientName + '" id="editingredientName">');

        row.find(".editbutton").empty();
        row.find(".editbutton").html('<button class="savebtnIngredient">Gem</button>');

    })
}

function listenersaveIngredient() {
    $("#container").on('click', '.savebtnIngredient', function () {
        var row = $(this).closest('tr');
        var ingredientId = row.find(".ingredientID").text();
        var ingredientName = row.find("#editingredientName").val();

        var origingredientName = row.find("#editingredientName").attr('placeholder');


        var params = "?ingredientId=" + ingredientId;

        if (ingredientName !== "")
            params = params + "&ingredientName=" + ingredientName;
        else
            params = params + "&ingredientName=" + origingredientName;

        Agent.PUT("rest/ingredient" + params, null, function (data) {
            row.replaceWith(generateIngredientHtml(data));
        }, function (data) {
            window.alert("Ændringer ikke gemt: " + $(data.responseText).find("u").first().text());
            console.log(data)
        })

    })

}

document.addEventListener('DOMContentLoaded', function () {
    listener();
    listeneredit();
    listenersave();
    listenerdelete();
    componentlistenerAdd();
    listenereditIngredient();
    listenersaveIngredient();
});