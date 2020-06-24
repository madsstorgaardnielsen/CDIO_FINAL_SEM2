function init() { //runs when page is loaded to get employee id
    if (document.location.search !== '') {
        var ID = location.search.replace(/^.*?\=/, '');
        $("#container").attr('data-id',''+ ID);
        $("#laborantHome").attr('href', 'Laborant.html?ID=' + $("#container").attr('data-id'));
    } else {
        $("#container").attr('data-id',''+ localStorage.getItem("ID"));
        $("#laborantHome").attr('href', 'Laborant.html?ID=' + $("#container").attr('data-id'));
    }
}

function getPID() { //sets up html to ask user for productbatchID
    Agent.GET("rest/productBatch", function (data) {
        $("#optionsbox").html('' +
            '<table class="optionstable"><thead><tr>' +
            '<th>Productbatch ID</th>' +
            '<th>Recept ID</th>' +
            '</tr></thead>' +
            '<tbody id="tablebody"></tbody>' +
            '</table>'
        );
        $.each(data, function () {
            $("#tablebody").append('' +
                '<tr>' +
                '<td>'+ this.productBatchId +'</td>' +
                '<td>'+ this.recipeId +'</td>'+
                '</tr>'
            )
        });

    }, function (data) {
        $("#optionsbox").html('' +
            '<div class="boxedText" id="error">'
            + $(data.responseText).find("u").first().text() +
            '</div>'
        )
    });
    $("#header").text("Indtast Produktbatch ID");
    $("#container").html('' +
        '<form action="javascript:getRecipeName()">' +
        '<input type="number" placeholder="Nr." id="batchID" step="0.0001" min="0">' +
        '<button class="btn">Ok</button>' +
        '</form>'
    )
}

function getRecipeName() { //gets info from database about productbatch and the recipe, and shows to user
    $("#container").attr('data-batchID', ''+ $("#batchID").val());
    var batchID = $("#container").attr('data-batchID');
    $("#optionsbox").empty();
    Agent.GET("rest/productBatch/"+ batchID +'/', function (data) {
        $("#container").attr('data-recipeID', data.recipeId);
        Agent.GET("rest/recipe/"+ data.recipeId +'/', function (data) {
            $("#header").text("Recept navn");
            $("#container").html('' +
                '<form action="javascript:setStatus()">' +
                '<div class="boxedText">'+ data.recipeName +'</div>' +
                '<button class="btn">Videre</button>' +
                '</form>'
            );
        }, function (data) {
            $("#error").remove();
            console.log(data);
            $("#container").append('' +
                '<div class="errorcont"><div class="boxedText" id="error">'+
                'Bruger ikke tilføjet: '+ $(data.responseText).find("u").first().text() +
                '</div></div>'
            );
        });
    }, function (data) {
        $("#error").remove();
        console.log(data);
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            'Bruger ikke tilføjet: '+ $(data.responseText).find("u").first().text() +
            '</div></div>'
        );
    });
}

function setStatus() { //gets the next component to be weighed and asks user for tara value
    var batchID = $("#container").attr('data-batchID');
    Agent.GET("rest/productBatchComponent/afvejning/getproductbatchcomponent/"+ batchID +'/',function (data) {
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
                '<input type="number" placeholder="kg" id="tarainput" step="0.0001" min="0">' +
                '<button class="btn">Ok</button>' +
                '</form>' +
                '</div>'
            );
            $("#container").attr('data-compID', data.id);
            $("#container").attr('data-ingredientID', data.ingredientID);
            $("#container").attr('data-ingredientName', data.ingredientName);
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

function setTara() { //gets ingredientbatchId from user and shows options to user
    var compID = $("#container").attr('data-compID');
    $("#container").attr('data-tara',''+ $("#tarainput").val());
    var tara = $("#container").attr('data-tara');

    Agent.GET("rest/ingredientbatch/byIngredient/" + $("#container").attr('data-ingredientID'), function (data) {
        $("#optionsbox").html('' +
            '<div class="boxedText">Råvarebatch af type: '+ $("#container").attr("data-ingredientName") +'</div>' +
            '<table class="optionstable"><thead><tr>' +
            '<th>Råvarebatch ID</th>' +
            '<th>Råvare mængde</th>' +
            '</tr></thead>' +
            '<tbody id="tablebody"></tbody>' +
            '</table>'
        );
        $.each(data, function () {
            $("#tablebody").append('' +
                '<tr>' +
                '<td>'+ this.ingredientBatchId +'</td>' +
                '<td>'+ this.amount +' kg</td>'+
                '</tr>'
            )
        });
    }, function (data) {
        $("#optionsbox").html('' +
            '<div class="boxedText" id="error">'
            + $(data.responseText).find("u").first().text() +
            '</div>'
        )
    });

    $("#header").text("Indtast Råvarebatch");
    $(".errorcont").remove();
    $("#subcontainer").html('' +
        '<form action="javascript:setRaavare()">' +
        '<input type="number" placeholder="Nr." id="raavarebatchinput">' +
        '<button class="btn">Ok</button>' +
        '</form>'
    );
}

function setRaavare() { //validates ingredientbatchID and asks user for brutto weight
    var compID = $("#container").attr('data-compID');
    $("#container").attr('data-raavareBatch',''+ $("#raavarebatchinput").val());
    var raavareBatch = $("#container").attr('data-raavareBatch');
    $("#optionsbox").empty();
    Agent.GET("rest/productBatchComponent/validateBatch/"+ compID +"/"+ raavareBatch +'/', function () {
        $("#header").text("Indtast brutto vægt");
        $(".errorcont").remove();
        $("#subcontainer").html('' +
            '<form action="javascript:setBrutto()">' +
            '<input type="number" placeholder="Kg" id="bruttoInput" step="0.0001" min="0">' +
            '<button class="btn">Ok</button>' +
            '</form>'
        )
    }, function (data) {
        $(".errorcont").remove();
        console.log(data);
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            $(data.responseText).find("u").first().text() +
            '</div></div>'
        );
    })
}

function setBrutto() { //sends the input data to UserAPI to be saved
    var batchcomp = {}; //creating ProductBatchComponentDTO
    batchcomp.id = $("#container").attr('data-compID');
    batchcomp.ingredientBatchID = $("#container").attr('data-raavarebatch');
    batchcomp.laborantID = $("#container").attr('data-id');
    batchcomp.tara = $("#container").attr('data-tara');
    batchcomp.brutto = $("#bruttoInput").val();
    batchcomp.terminal = 1;
    batchcomp.productBatchID = $("#container").attr('data-recipeID');
    batchcomp.ingredientID = $("#container").attr('data-ingredientID');

    //Sending request to save data
    Agent.PUT("rest/productBatchComponent", batchcomp, function () { //procedure for status ok
        $(".errorcont").remove();
        $("#container").html('' +
            '<form action="javascript:setStatus()">' +
            '<div class="boxedText">Afvejning gemt</div>' +
            '<button class="btn">Videre</button>' +
            '</form>'
        )
    }, function (data) { //procedure for bad status
        $("#error").remove();
        console.log(data);
        //show error message
        $("#container").append('' +
            '<div class="errorcont"><div class="boxedText" id="error">'+
            $(data.responseText).find("u").first().text() +
            '</div></div>'
        );
    });
}

document.addEventListener('DOMContentLoaded', function () { //listener to run initialising function
    init();
});