

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
        '<button class="confirmbtn" id="addnewib">Opret råvare batch</button>'
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
        '<td class= ibAmount>' + ingredientBatch.amount + ' kg</td>' +
        '<td class= supplier>' + ingredientBatch.supplier + '</td>' +
        '</tr>'
}

function listenerAdd2() { //shows line to add new batch
    $("#container").on('click', "#addnewib", function () {
        $("#header").html('Tilføj råvare batch'+
        '<br>');
        $("#container").html('' +
            '<form action="javascript:saveBatch()">' +
            '<input id="ibidinput" type="number" placeholder="Råvare Batch ID" name="ibid" required>' +
            '<input id="ingidinput" type="number" placeholder="Råvare ID" name="ingid" required>'+
            '<input id="amountinput" type="number" placeholder="Mængde i kg" name="amount" step="0.0001" min="0" required>' +
            '<input id="supplierinput" type="text" placeholder="Leverandør" name="supplier">'+
            '<br>' +
            '<button id="finishibbtn" class="btn">Udfør</button>' +
            '</form>'
        );
        $("#optionsbox").html('<h2>Råvarer</h2>' +
            '<table class="optionstable"> <thead>' +
            '<tr>' +
            '<td>Råvare ID</td>' +
            '<td>Råvare navn</td>' +
            '</tr> </thead>' +
            '<tbody id="inglistbody"></tbody>' +
            '</table>'
        );
        Agent.GET("rest/ingredient", function (data) {
            $.each(data, function () {
                $("#inglistbody").append(genIngList(this));
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

function saveBatch() {//when click udfør then add productbatch
        var ibdto = {};
        ibdto.ingredientBatchId = $("#ibidinput").val();
        ibdto.ingredientId =  $("#ingidinput").val();
        ibdto.amount = $("#amountinput").val();
        ibdto.supplier = $("#supplierinput").val();

        Agent.POST("/rest/ingredientbatch", ibdto,function (){
            $("#container").html('' +
                '<form action="javascript:getAllIngredientBatch()">' +
                '<div class="boxedText">Råvarebatch oprettet</div>' +
                '<button class="btn">Videre</button>' +
                '</form>');
            $("#optionsbox").html('');
        },function (data){
            $("#error").remove();
            console.log(data);
            $("#container").append('' +
                '<div class="errorcont"><div class="boxedText" id="error">'+
                'Fejl: '+ $(data.responseText).find("u").first().text() +
                '</div></div>'
            );
        } );
}

document.addEventListener('DOMContentLoaded', function () {
    listenerAdd2();
});