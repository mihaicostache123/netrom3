function makeTable(container, data) {
    var table = $("<table/>").addClass('playerTable');

    var thead= $("<thead/>");
    var headerRow = $("<tr/>");


    headerRow.append($("<th>").text("ID"));
    headerRow.append($("<th/>").text("Name"));
    headerRow.append($("<th/>").text("Age"));
    headerRow.append($("<th/>").text("Position"));
    headerRow.append($("<th/>").text("Number"));
    headerRow.append($("<th/>").text("Signing Date"));
    headerRow.append($("<th/>").text("Team"));

    thead.append(headerRow);
    table.append(thead);
    table.append("<tbody>");
    $.each(data, function (rowIndex, r) {
        var row = $("<tr/>");
        $.each(r, function (colIndex, c) {
            if(colIndex == "position"){
               if(c == 1){
                     row.append($("<td/>").text("Forward"));
               }
               else if(c == 2){
                     row.append($("<td/>").text("Midfielder"));
               }
               else if(c == 3){
                     row.append($("<td/>").text("Defender"));
               }
               else if(c == 4){
                     row.append($("<td/>").text("Goalkeeper"));
               }
            }
            else if (colIndex == "team") {
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
        url: "http://localhost:8080/player/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var playerTableContainer = $("#playerTableContainer");
            makeTable(playerTableContainer, data);
            $(".playerTable").DataTable()
        },
        error: function (data) {
            alert('Error: ' + data);
        }
    });
});

function showForm() {
    var addForm = document.getElementById("addForm");
     if (addForm.style.display === "block") {
            addForm.style.display = "none";
        }
        else {
            addForm.style.display = "block";
        }
}

function createPlayer() {
        var name = $('#name').val();
        var age = $('#age').val();
        var position = $('#position').val();
        var number = $('#number').val();
        var date = $('#date').val();
        var teamIdValue = $('#team').val();
        teamId = Number(teamIdValue);
        var data = {
            name: name,
            age: age,
            position: position,
            number: number,
            signingDate: date,
            team: {
                id: teamId
            }
        };
        if(data.name == "" || data.age == "" || data.position == "" || data.number == "" || data.date == "" || data.team == ""){
            alert("Please fill all the fields");
        }
        else{
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/player/add',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                console.log('Player added successfully');
                window.location.reload();
            },
            error: function(error) {
                console.log('Error:', error);
            }
        });
        }
};

function deletePlayer() {
    var id = $('#deleteId').val();
    id = Number(id);
    var data = {
        id: id
    }
    if(data.id == ""){
        alert("Please fill in the ID field");
    }
    else{
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/player/delete/' + id,
        success: function(response) {
            window.location.reload();
        },
        error: function(error) {
            console.log('Error:', error);
        }
    });
    }
}

function deleteAllPlayers(){
    var confirmation = confirm("Are you sure you want to delete all players?");
    if(confirmation == true){
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/player/deleteAll',
        success: function(response) {
            window.location.reload();
        },
        error: function(error) {
            console.log('Error:', error);
        }
    });
    }
}

function editPlayer(){
    var id= $('#editId').val();
    id = Number(id);
    var name = $('#editName').val();
    var age = $('#editAge').val();
    var position = $('#editPosition').val();
    var number = $('#editNumber').val();
    var date = $('#editDate').val();
    var teamIdValue = $('#editTeam').val();
    teamId = Number(teamIdValue);
    var data = {
        id: id,
        name: name,
        age: age,
        position: position,
        number: number,
        signingDate: date,
        team: {
            id: teamId
        }
    }
    if(data.id == "" || data.name == "" || data.age == "" || data.position == "" || data.number == "" || data.date == "" || data.team == ""){
        alert("Please fill all the fields");
    }
    else{
        $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/player/update/' + id,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(response) {
            window.location.reload();
        },
        error: function(error) {
            console.log('Error:', error);
        }
    });
    }
}
