function getAllIngredientBatch() { //shows all productbatches
    $("#header").text("Råvare Batch Liste");
    $("#container").html(
        '<table> <thead> <tr>' +
        '<th>Råvare Batch ID</th>' +
        '<th>Råvare ID</th>' +
        '<th>Mængde</th>' +
        '<th>Leverandør</th>' +
     //   '<th colspan="1"></th>' +
        '</tr> </thead> ' +
        '<tbody id="tablebody"></tbody> ' +
        '</table>' +
        '<button class="confirmbtn">Tilføj ny</button>'
    );
    var row;
    Agent.GET("rest/ingredientbatch", function (data) {
        $.each(data, function () {
            row = $("#tablebody").append(genIngBatchList(this));
        });

    }, function (data) {
        $("#container").html($(data.responseText).find("u").first().text());
    });
}

function genIngBatchList(ingredientBatch) { //generates html to show in recipeTable
    return '<tr> ' +
        '<td class = ingredientBatchID>' + ingredientBatch.ingredientBatchId + '</td>' +
        '<td class= ingredientID>' + ingredientBatch.ingredientId + '</td>' +
        '<td class= ibAmount>' + ingredientBatch.amount + '</td>' +
        '<td class= supplier>' + ingredientBatch.supplier + '</td>' +
      //  '<td class= viewbutton> <button class="viewbtn" onclick="getProductBatch('+productBatch.productBatchId+')">Se mere</button></td>' +
        '</tr>'
}