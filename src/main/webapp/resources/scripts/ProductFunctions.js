function getAllProducts() { //shows all productbatches
    $("#header").text("Produkt Batch Liste");
    $("#container").html(
        '<table> <thead> <tr>' +
        '<th>Produkt Batch ID</th>' +
        '<th>Recept ID</th>' +
        '<th>Status</th>' +
        '<th>BrugerID</th>' +
        '<th>Oprettet</th>' +
        '<th>Færdig</th>' +
        '<th colspan="1"></th>' +
        '</tr> </thead> ' +
        '<tbody id="tablebody"></tbody> ' +
        '</table>' +
        '<div class="infocontainer" id="inputID">' +
        '<button class="confirmbtn" id="viewbtn">Opret produkt batch</button>' +
        '</div>'
    );
    $("#optionsbox").html('');
    var row;
    Agent.GET("rest/productBatch", function (data) {
        $.each(data, function () {
            if(this.status === 0){ this.status = "Startet";}
            if(this.status === 1){ this.status = "Under produktion";}
            if(this.status === 2){this.status = "Afsluttet"}
            row = $("#tablebody").append(generateProductBatchList(this));
        });

    }, function (data) {
        $("#error").remove();
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            'Produkt batches kunne ikke hentes: '+ $(data.responseText).find("u").first().text() +
            '</div></div>');
    });
}


function generateProductBatchList(productBatch) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = productBatchID>' + productBatch.productBatchId + '</td>' +
        '<td class= recipeID>' + productBatch.recipeId + '</td>' +
        '<td class= status>' + productBatch.status + '</td>' +
        '<td class= userId>' + productBatch.userId + '</td>' +
        '<td class= creationDate>' + productBatch.creationDate + '</td>' +
        '<td class= finishDate>' + productBatch.finishDate + '</td>' +
        '<td class= viewbutton> <button class="viewbtn" onclick="getProductBatch('+productBatch.productBatchId+')">Se mere</button></td>' +
        '</tr>'
}

function getProductBatch(productBatchID){ //shows one product batch and all its components
    $("#header").text("Produkt Batch "+productBatchID);
    $("#container").html('<table> <thead> <tr>' +
        '<th>Produkt Batch ID</th>' +
        '<th>Recept ID</th>' +
        '<th>Status</th>' +
        '<th>BrugerID</th>' +
        '<th>Oprettet</th>' +
        '<th>Færdig</th>' +
        '</tr> </thead> ' +
        '<tbody id="batchtable"></tbody>'+
        '</table>'+
        '<H3>Komponenter</H3>'+
        '<table> <thead> <tr>' +
        '<th>PB Komp ID</th>' +
        '<th>Råvare</th>' +
        '<th>Råvare ID</th>' +
        '<th>Mængde</th>' +
        '<th>Tolerance</th>' +
        '<th>Afvejers bruger ID</th>' +
        '<th>Råvare batch ID</th>' +
        '<th>Tara</th>' +
        '<th>Netto</th>' +
        '</tr> </thead> ' +
        '<tbody id="tablebody"></tbody> ' +
        '</table>'
    );
    Agent.GET("/rest/productBatch/"+productBatchID+"/", function (data) {
        if(data.status === 0){ data.status = "Startet";}
        if(data.status === 1){ data.status = "Under produktion";}
        if(data.status === 2){ data.status = "Afsluttet";}
        $("#batchtable").append(generateProductBatchHeader(data));
        $.each(data.components, function(){
            $("#tablebody").append(generateCompList(this));
        });
        $("#tablebody").append('<br>'+
            '<button class="confirmbtn" id="printbtn" onclick="window.print()">Print produkt batch</button>');
    }, function (data){
            $("#error").remove();
            $("#container").append('' +
                '<div class="errorcont"><div class="boxedText" id="error">'+
                'Kunne ikke hente produkt batch: '+ $(data.responseText).find("u").first().text() +
                '</div></div>');
    });
}

function generateProductBatchHeader(productBatch) { //generates the "header" table of the batch
    return '<tr> ' +
        '<td class = productBatchID>' + productBatch.productBatchId + '</td>' +
        '<td class= recipeID>' + productBatch.recipeId + '</td>' +
        '<td class= status>' + productBatch.status + '</td>' +
        '<td class= userId>' + productBatch.userId + '</td>' +
        '<td class= creationDate>' + productBatch.creationDate + '</td>' +
        '<td class= finishDate>' + productBatch.finishDate + '</td>' +
        '</tr>'
}

function generateCompList(component) { //generates html for rows for each component
    var tara;
    var netto;
    if (component.tara === null || component.netto === null) {
        tara = 0;
        netto = 0;
    } else {
        tara = component.tara;
        netto = component.tara;
    }
    return '<tr> ' +
        '<td class = productBatchCompID>' + component.id + '</td>' +
        '<td class= ingredientName>' + component.ingredientName + '</td>' +
        '<td class= ingredientID>' + component.ingredientID + '</td>' +
        '<td class= amount>' + component.amount + ' kg</td>' +
        '<td class= tolerance>' + component.tolerance + ' %</td>' +
        '<td class= laborantID>' + component.laborantID + '</td>' +
        '<td class= ingredientBatchID>' + component.ingredientBatchID + '</td>' +
        '<td class= tara>' + tara + ' kg</td>' +
        '<td class= netto>' + netto + ' kg</td>' +
        '</tr>';
}

function listenerAdd() {//creates page for adding product batch
    $("#container").on('click', "#viewbtn", function () {
        $("#header").text("Tilføj produkt batch");
        $("#container").html('' +
            '<form action="javascript:confirmAddPB()">' +
            '<input id="receptidinput" type="text" placeholder="Indsæt recept ID" name="receptid" required>' +
            '<br>' +
            '<button id="finishbtn" class="btn" type="submit" >Tilføj</button>' +
            '</form>'
        );
        $("#optionsbox").html( //setup start of table for recipes
                    '<table class="optionstable"> <thead> <tr>' +
                    '<th>Recept ID</th>' +
                    '<th>Recept Navn</th>' +
                    '</tr> </thead> ' +
                    '<tbody id="opttablebody"></tbody> ' +
                    '</table>'
                );

        Agent.GET("rest/recipe", function (data) { //gets all recipes and makes a table from it
            $.each(data, function () {
                $("#opttablebody").append(generateRecipeHtml1(this));
            });
        }, function (data) {
            $("#error").remove();
            $("#container").append('' +
                '<div class="errorcont"><div class="boxedText" id="error">'+
                'Kunne ikke hente recepter: '+ $(data.responseText).find("u").first().text() +
                '</div></div>');
        });
    })
}
function generateRecipeHtml1(recipe) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = recipeID>' + recipe.recipeID + '</td>' +
        '<td class= recipeName>' + recipe.recipeName + '</td>' +
        '</tr>'
}

//when click udfør then add productbatch
function confirmAddPB() { // triggered when click "#finishbtn"
        var receptidIn = $("#receptidinput").val();
        var userID = $("#container").attr("data-id");

        Agent.POST("/rest/productBatch/"+ receptidIn +"/"+userID+"/", null,function (data){
            $("#header").text("Produkt batch tilføjet!");
            $("#container").html('<button class="btn" onclick="getAllProducts()">Videre</button>')
        },function (data){
            alert("Kan ikke oprette produkt batch ud fra dette recept id");
            $("#error").remove();
            $("#container").append('' +
                '<div class="errorcont"><div class="boxedText" id="error">'+
                'Produkt batch kunne ikke tilføjes: '+ $(data.responseText).find("u").first().text() +
                '</div></div>');
        } );
    }


function init() {
    if (document.location.search !== '') {
        var ID = location.search.replace(/^.*?\=/, '');
        $("#container").attr('data-id',''+ ID);
        $("#laborantHome").attr('href', 'Laborant.html?ID=' + $("#container").attr('data-id'));
    } else {
        $("#container").attr('data-id',''+ localStorage.getItem("ID"));
        $("#laborantHome").attr('href', 'Laborant.html?ID=' + $("#container").attr('data-id'));
    }
}
document.addEventListener('DOMContentLoaded', function () { //initiates listeners when doc is loaded.
    init();
    listenerAdd();
    listenerAdd1();
});