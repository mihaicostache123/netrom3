function makeTable(container, data) {
    var table = $("<table/>").addClass('gameTable');

    var thead = $("<thead/>");
    var headerRow = $("<tr/>");

    headerRow.append($("<th/>").text("ID"));
    headerRow.append($("<th/>").text("Score1"));
    headerRow.append($("<th/>").text("Score2"));
    headerRow.append($("<th/>").text("Date"));
    headerRow.append($("<th/>").text("Team1"));
    headerRow.append($("<th/>").text("Team2"));

    thead.append(headerRow);
    table.append(thead);
    table.append("<tbody>");
    $.each(data, function (rowIndex, r) {

        var row = $("<tr/>");
        $.each(r, function (colIndex, c) {
            if(colIndex == "team1" || colIndex == "team2"){
                row.append($("<td/>").text(c.name));
            }
            else{
                row.append($("<td/>").text(c));
            }
        });
        table.append(row);
    });
    table.append("</tbody>");
    return container.append(table);
}

$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/game/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var gameTableContainer = $("#gameTableContainer");
            makeTable(gameTableContainer, data);
            $(".gameTable").DataTable()
        },
        error: function (data) {
            alert('Error: ' + data);
        }
    });
});


function createGame(){
    var date = $("#date").val();
    var score1 = $("#score1").val();
    var score2 = $("#score2").val();
    var team1IdValue = $("#team1").val();
    var team2IdValue = $("#team2").val();
    team1Id = Number(team1IdValue);
    team2Id = Number(team2IdValue);
    score11= Number(score1);
    score22 = Number(score2);
    var data = {
        date: date,
        score1: score1,
        score2: score2,
        team1: {
            id: team1Id
        },
        team2: {
            id: team2Id
        }
    };
    if(data.date == "" || data.score1 == "" || data.score2 == "" || data.team1Id == "" || data.team2Id == ""){
        alert("Please fill in all fields");
    }
    else if(team1Id == team2Id){
        alert("Team1 and Team2 cannot be the same");
    }
    else{
    $.ajax({
        url: "http://localhost:8080/game/add",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.reload();
        },
        error: function (error) {
            alert('Error: ' + data);
        }
    });
    }
};

function deleteGame(){
    var id = $("#deleteId").val();
    var data = {
        id: id
    };
    if(data.id == ""){
        alert("Please fill in all fields");
    }
    else{
    $.ajax({
        url: "http://localhost:8080/game/delete/" + id,
        type: "DELETE",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.reload();
        },
        error: function (error) {
            alert('Error: ' + data);
        }
    });
    }
}

function deleteAllGames(){
    var confirmation = confirm("Are you sure you want to delete all games?");
    if(confirmation == true){
    $.ajax({
        url: "http://localhost:8080/game/deleteAll",
        type: "DELETE",
        contentType: "application/json",
        success: function (response) {
            window.location.reload();
        },
        error: function (error) {
            alert('Error: ' + data);
        }
    });
    }
}

function editGame(){
    var id = $("#editId").val();
    var date = $("#editDate").val();
    var score1 = $("#editScore1").val();
    var score2 = $("#editScore2").val();
    var team1IdValue = $("#editTeam1").val();
    var team2IdValue = $("#editTeam2").val();
    team1Id = Number(team1IdValue);
    team2Id = Number(team2IdValue);
    score11= Number(score1);
    score22 = Number(score2);
    var data = {
        id: id,
        date: date,
        score1: score1,
        score2: score2,
        team1: {
            id: team1Id
        },
        team2: {
            id: team2Id
        }
    };
    if(data.id == "" || data.date == "" || data.score1 == "" || data.score2 == "" || data.team1Id == "" || data.team2Id == ""){
        alert("Please fill in all fields");
    }
    else if(team1Id == team2Id){
        alert("Team1 and Team2 cannot be the same");
    }
    else{
    $.ajax({
        url: "http://localhost:8080/game/update/" + id,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.reload();
        },
        error: function (error) {
            alert('Error: ' + data);
        }
    });
    }
}