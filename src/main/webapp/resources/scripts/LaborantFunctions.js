function init() {
    if (document.location.search !== '') {
        var ID = location.search.replace(/^.*?\=/, '');
        $("#laborantHome").attr('data-id',''+ ID);
        $("#laborantHome").attr('href', 'Laborant.html?ID=' + $("#laborantHome").attr('data-id'));
    } else {
        $("#laborantHome").attr('data-id',''+ localStorage.getItem("ID"));
        $("#laborantHome").attr('href', 'Laborant.html?ID=' + $("#laborantHome").attr('data-id'));
    }
}

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
    Agent.PUT("rest/afvejning/getproductbatchcomponent/" + batchID, null ,function (data) {
        $("#header").text("Indtast Tara");
        $("#container").html('' +
            '<form action="javascript:setTara('+ data +')">' +
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
        $("#header").text("Indtast raavarebatch");
        $("#container").html('' +
            '<form action="javascript:setRaavare('+ compID +')">' +
            '<input type="text" placeholder="Nr." id="raavarebatchinput">' +
            '<button class="btn">Ok</button>' +
            '</form>'
        )
    }, function (data) {

    })
}

function setRaavare(compID) {
    var raavareBatch = $("#raavarebatchinput").val();
    Agent.PUT("rest/productBatchComponent/setbatch/"+ compID +"/"+ raavareBatch, null, function () {

    })
}

document.addEventListener('DOMContentLoaded', function () {
    init();
});