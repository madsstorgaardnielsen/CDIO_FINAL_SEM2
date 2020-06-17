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
        '<th>Produkt batch komponenet ID</th>' +
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
        $("#batchtable").append(generateProductBatchHeader(data))
        $.each(data.components, function(){
            $("#tablebody").append(generateCompList(this));
        })
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
        '<td class= amount>' + component.amount + '</td>' +
        '<td class= tolerance>' + component.tolerance + '</td>' +
        '<td class= laborantID>' + component.laborantID + '</td>' +
        '<td class= ingredientBatchID>' + component.ingredientBatchID + '</td>' +
        '<td class= tara>' + component.tara + '</td>' +
        '<td class= netto>' + component.netto + '</td>' +
        '</tr>'
}

function listenerAdd() {
    $("#container").on('click', "#viewbtn", function () {
        $("#inputID").html('' +
            '<form>' +
            '<input id="receptidinput" type="text" placeholder="Indsæt recept ID" name="receptid" required>' +
            '<br>' +
            '<button class="btn" type="submit" onclick="addProductBatch()">Udfør</button>' +
            '</form>'
        )
    })
}

function addProductBatch(){
    /*lortet virker ikke

    var receptid_in =$("#receptidinput").val;
    var userID = $("#container").attr("data-id");
    Agent.POST("/rest/productBatch/"+receptid_in+"/"+userID+"/", userID ,function (data){
        alert("produktbatch oprettet");
    },function (data){
        alert("fejl");
    } )
*/
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
});