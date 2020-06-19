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
        '<button class="confirmbtn" id="viewbtn">Tilføj ny</button>' +
        '</div>'
    );
    var row;
    Agent.GET("rest/productBatch", function (data) {
        $.each(data, function () {
            if(this.status == 0){ this.status = "Startet";}
            if(this.status == 1){ this.status = "Under produktion";}
            if(this.status == 2){this.status = "Afsluttet"}
            row = $("#tablebody").append(generateProductBatchList(this));
        });

    }, function (data) {
        $("#container").html($(data.responseText).find("u").first().text());
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
        '<th>Produktbatch komponenet ID</th>' +
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
        if(data.status == 0){ data.status = "Startet";}
        if(data.status == 1){ data.status = "Under produktion";}
        if(data.status == 2){ data.status = "Afsluttet";}
        $("#batchtable").append(generateProductBatchHeader(data));
        $.each(data.components, function(){
            $("#tablebody").append(generateCompList(this));
        });
        $("#tablebody").append('<br>'+
            '<button class="confirmbtn" id="printbtn" onclick="window.print()">Print produkt batch</button>');
    }, function (data){
        $("#container").html($(data.responseText).find("u").first().text());

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
    return '<tr> ' +
        '<td class = productBatchID>' + component.id + '</td>' +
        '<td class= ingredientName>' + component.ingredientName + '</td>' +
        '<td class= ingredientID>' + component.ingredientID + '</td>' +
        '<td class= amount>' + component.amount + ' kg</td>' +
        '<td class= tolerance>' + component.tolerance + ' %</td>' +
        '<td class= laborantID>' + component.laborantID + '</td>' +
        '<td class= ingredientBatchID>' + component.ingredientBatchID + '</td>' +
        '<td class= tara>' + component.tara + ' kg</td>' +
        '<td class= netto>' + component.netto + ' kg</td>' +
        '</tr>'
}

function listenerAdd() {//shows line to add new batch by recipe id
    $("#container").on('click', "#viewbtn", function () {
        $("#inputID").html('' +
            '<form>' +
            '<input id="receptidinput" type="text" placeholder="Indsæt recept ID" name="receptid" required>' +
            '<br>' +
            '<button id="finishbtn" class="btn" type="submit" >Udfør</button>' +
            '</form>'
        )
    })
}
function listenerAdd1() {//when click udfør then add productbatch
    $("#container").on('click', "#finishbtn", function () {
        var receptidIn = $("#receptidinput").val();
        var userID = $("#container").attr("data-id");

        Agent.POST("/rest/productBatch/"+ receptidIn +"/"+userID+"/", null,function (data){
            getAllProducts();
        },function (data){
            alert("fejl");
        } )

        $("#inputID").html('' +
            '<form>' +
            '<input id="receptidinput" type="text" placeholder="Indsæt recept ID" name="receptid" required>' +
            '<br>' +
            '<button id="viewbtn" class="btn" type="submit" >Udfør</button>' +
            '</form>'
        )
    })
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
document.addEventListener('DOMContentLoaded', function () {
    init();
    listenerAdd();
    listenerAdd1();
});