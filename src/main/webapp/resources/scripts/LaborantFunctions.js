function getPID() {
    $("#header").text("Indtast produktbatch ID");
    $("#container").html('' +
        '<form action="javascript:getRecipeName()">' +
        '<input type="text" placeholder="Nr." id="pID">' +
        '<button class="btn">Ok</button>' +
        '</form>'
    )
}

function getRecipeName() {
    var ID = $("#pID").val();

    Agent.GET("rest/productBatch/recipe/afvejning/" + ID, function (data) {
        $("#header").text("Recept navn");
        $("#container").html('' +
            '<form action="javascript:setStatus('+ ID +')">' +
            '<div class="boxedText">'+ data.responseText +'</div>' +
            '<button class="btn">Videre</button>' +
            '</form>'
        )
    });
}

function setStatus(batchID) {
    Agent.PUT("rest/productBatch/afvejning/" + batchID, null ,function (data) {
        $("#header").text("Indtast Tara");
        $("#container").html('' +
            '<form action="javascript:setTara('+ data.responseText +')">' +
            '<input type="text" placeholder="kg" id="tarainput">' +
            '<button class="btn">Ok</button>' +
            '</form>'
        )
    }, function (data) {

    })
}

function setTara(compID) {
    var tara = $("#tarainput").val();
    Agent.PUT("rest/productBatchComponent/" + compID + "/" + tara, null, function () {

    }, function (data) {

    })
}