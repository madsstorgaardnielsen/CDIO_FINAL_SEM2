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

function getPID() {
    $("#header").text("Indtast produktbatch ID");
    $("#container").html('' +
        '<form action="javascript:getRecipeName()">' +
        '<input type="text" placeholder="Nr." id="batchID">' +
        '<button class="btn">Ok</button>' +
        '</form>'
    )
}

function getRecipeName() {
    $("#container").attr('data-batchID', ''+ $("#batchID").val());
    var batchID = $("#container").attr('data-batchID');
    Agent.GET("rest/productBatch/"+ batchID +'/', function (data) {
        Agent.GET("rest/recipe/"+ data.recipeId +'/', function (data) {
            $("#header").text("Recept navn");
            $("#container").html('' +
                '<form action="javascript:setStatus()">' +
                '<div class="boxedText">'+ data.recipeName +'</div>' +
                '<button class="btn">Videre</button>' +
                '</form>'
            );
        }, function (data) {

        });
    }, function (data) {

    });
}

function setStatus() {
    var batchID = $("#container").attr('data-batchID');
    Agent.GET("rest/productBatch/afvejning/getproductbatchcomponent/"+ batchID +'/',function (data) {
        if (data !== "done") {
            $("#header").text("Indtast Tara");
            $("#container").html('' +
                '<div id="batchInfo" class="infocontainer">' +
                '<div class="boxedText"> ' +
                '<div>Product batch komponent ID: </div><div class="boxedText">' + data.id + '</div>' +
                '<div>Ingrediens Navn: </div><div class="boxedText">' + data.ingredientName + '</div>' +
                '<div>Mængde: </div><div class="boxedText">' + data.amount + '</div>' +
                '<div>Tolerance: </div><div class="boxedText">' + data.tolerance + '</div>' +
                '</div>' +
                '</div>' +
                '<div id="subcontainer">' +
                '<form action="javascript:setTara()">' +
                '<input type="text" placeholder="kg" id="tarainput">' +
                '<button class="btn">Ok</button>' +
                '</form>' +
                '</div>'
            );
            $("#container").attr('data-compID', data.id)
        } else {
            $("#container").html('' +
                '<div class="boxedText">Produkt batch færdiggjort</div>'
            )
        }
    }, function (data) {

    })
}

function setTara() {
    var compID = $("#container").attr('data-compID');
    $("#container").attr('data-tara',''+ $("#tarainput").val());
    var tara = $("#container").attr('data-tara');
    Agent.GET("rest/productBatchComponent/" + compID + "/" + tara +'/', function () {
        $("#header").text("Indtast raavarebatch");
        $("#subcontainer").html('' +
            '<form action="javascript:setRaavare()">' +
            '<input type="text" placeholder="Nr." id="raavarebatchinput">' +
            '<button class="btn">Ok</button>' +
            '</form>'
        )
    }, function (data) {

    })
}

function setRaavare() {
    var compID = $("#container").attr('data-compID');
    $("#container").attr('data-raavareBatch',''+ $("#raavarebatchinput").val());
    var raavareBatch = $("#container").attr('data-raavareBatch');
    Agent.GET("rest/productBatchComponent/validateBatch/"+ compID +"/"+ raavareBatch +'/', function () {
        $("#header").text("Indtast brutto vægt");
        $("#subcontainer").html('' +
            '<form action="javascript:setBrutto()">' +
            '<input type="text" placeholder="Kg" id="bruttoInput">' +
            '<button class="btn">Ok</button>' +
            '</form>'
        )
    }, function (data) {

    })
}

function setBrutto() {
    var batchcomp = {};
    batchcomp.id = $("#container").attr('data-compID');
    batchcomp.productBatchID = $("#container").attr('data-batchID');
    batchcomp.ingredientBatchID = $("#container").attr('data-raavarebatch');
    batchcomp.ingredientName = "null";
    batchcomp.amount = 0;
    batchcomp.tolerance = 0;
    batchcomp.laborantID = $("#container").attr('data-id');
    batchcomp.tara = $("#container").attr('data-tara');
    batchcomp.netto = 0;
    batchcomp.brutto = $("#bruttoInput").val();
    batchcomp.terminal = 1;

    Agent.PUT("rest/productBatchComponent", batchcomp, function () {
        $("#container").html('' +
            '<form action="javascript:setStatus()">' +
            '<div class="boxedText">Afvejning gemt</div>' +
            '<button class="btn">Videre</button>' +
            '</form>'
        )
    }, function (data) {
        console.log(batchcomp);
    });
}

document.addEventListener('DOMContentLoaded', function () {
    init();
});