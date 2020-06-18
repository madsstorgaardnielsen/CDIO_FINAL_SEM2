

function getAllIngredientBatch() { //shows all ingredientbatches in list
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
        '<button class="confirmbtn" id="addnewib">Tilføj ny</button>'
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

function genIngBatchList(ingredientBatch) { //generates html to show in IB list
    return '<tr> ' +
        '<td class = ingredientBatchID>' + ingredientBatch.ingredientBatchId + '</td>' +
        '<td class= ingredientID>' + ingredientBatch.ingredientId + '</td>' +
        '<td class= ibAmount>' + ingredientBatch.amount + '</td>' +
        '<td class= supplier>' + ingredientBatch.supplier + '</td>' +
        '</tr>'
}

function listenerAdd2() {//shows line to add new batch
    $("#container").on('click', "#addnewib", function () {
        $("#container").html('' +
            '<form>' +
            '<input id="ibidinput" type="text" placeholder="Råvare Batch ID" name="ibid" required>' +
            '<input id="ingidinput" type="text" placeholder="Råvare ID" name="ingid" required>'+
            '<input id="amountinput" type="text" placeholder="Mængde i kg" name="amount" required>' +
            '<input id="supplierinput" type="text" placeholder="Leverandør" name="supplier">'+
            '<br>' +
            '<button id="finishibbtn" class="btn">Udfør</button>' +
            '</form>'+
            '<br>'+
            '<table> <thead>' +
            '<tr>' +
            '<td>Råvare ID</td>' +
            '<td>Råvare navn</td>' +
            '</tr> </thead>' +
            '<tbody id="inglistbody"></tbody>' +
            '</table>'
        );
        var row;
        Agent.GET("rest/ingredient", function (data) {
            $.each(data, function () {
                row = $("#tablebody").append(genIngList(this));
            });

        }, function (data) {
            $("#container").html($(data.responseText).find("u").first().text());
        });
    })
}
function genIngList(ingredient){
    $("#inglistbody").append(
        '<tr>' +
        '<td>'+ingredient.ingredientID+'</td>'+
        '<td>'+ingredient.ingredientName+'</td>' +
        '</tr>'
    )
}

function listenerAdd3() {//when click udfør then add productbatch
    $("#container").on('click', "#finishibbtn", function () {
        var ibdto = {};
        ibdto.ingredientBatchId = $("#ibidinput").val();
        ibdto.ingredientId =  $("#ingidinput").val();
        ibdto.amount = $("#amountinput").val();
        ibdto.supplier = $("#supplierinput").val();

        Agent.POST("/rest/ingredientbatch", ibdto,function (){
            alert("Råvare batch oprettet med ID: "+ $("#ibidinput").val());
            getAllIngredientBatch();
        },function (){
            alert("fejl");
        } );
    })
}

document.addEventListener('DOMContentLoaded', function () {
    listenerAdd2();
    listenerAdd3()
});