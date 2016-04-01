/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function lister_parties() {
    $.get("partie/lister", function (data) {
        $('#content').html(data);
        
    });
}

function mapartie(idJoueur){
    $.get("partie/mapartie/"+idJoueur, function (data) {
        $('#content').html(data);
        decompte();
        timer = setInterval('decompte()', 1000);
    });
}

function montreSablier() {
    $('#dimmer').show();
    $('#loader').show();
}

function cacheSablier() {
    $('#dimmer').hide();
    $('#loader').hide();
}

function onLoad() {


    cacheSablier();


    $(document).ajaxSend(function () {
        montreSablier();
    });

    $(document).ajaxError(function () {
        cacheSablier();
    });

    $(document).ajaxSuccess(function () {
        cacheSablier();
    });

    lister_parties();

}

function pre_join(partieId) {

    $('#content').load("partie/pre_join/" + partieId, function (data) {
        $('#content').html(data);
    });
}
;

var timer;
var partieId;
function join() {
    partieId = $('input[name=partieId]').val();
    var nom = $('input[name=nom]').val();
    $.get("partie/join/" + partieId + "/" + nom, function (data) {
        $('#content').html(data);
        decompte();
        timer = setInterval('decompte()', 1000);
    });
}


var compte = 3;
function decompte()
{
    if (compte <= 1) {
        pluriel = "";
    } else {
        pluriel = "s";
    }

    document.getElementById("compt").innerHTML = compte;

    if (compte == 0 || compte < 0) {
        $.get("partie/refresh/"+partieId,function (data){
            $('#content').html(data);
        })
        compte = 4;
    }

    compte--;
}

function refresh_partie(){
    
    
    
}

