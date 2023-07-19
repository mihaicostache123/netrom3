function makeTable(container, data) {
    var table = $("<table/>").addClass('teamTable');

    var thead = $("<thead/>");
    var headerRow = $("<tr/>");

    headerRow.append($("<th/>").text("ID"));
    headerRow.append($("<th/>").text("Name"));
    headerRow.append($("<th/>").text("Country"));
    headerRow.append($("<th/>").text("TotalScoreHome"));
    headerRow.append($("<th/>").text("TotalScoreAway"));
    headerRow.append($("<th/>").text("TotalScore"));

    thead.append(headerRow);
    table.append(thead);
     table.append("<tbody>");
    $.each(data, function (rowIndex, r) {

        var row = $("<tr/>");
        $.each(r, function (colIndex, c) {
             row.append($("<td/>").text(c));
        });
        table.append(row);
    });
    table.append("</tbody>");
    return container.append(table);
}

$(document).ready(function () {
      $.ajax({
            url: "http://localhost:8080/team/all",
            type: "GET",
            dataType: "json",
            success: function (data) {
                    var teamTableContainer = $("#teamTableContainer");
                    makeTable(teamTableContainer, data);
                    $(".teamTable").DataTable()
            },
            error: function (data) {
                    alert('Error: ' + data);
            }
        });
});

function createTeam() {
    var name = $("#name").val();
    var country = $("#country").val();

    var data = {
        name: name,
        country: country
    };

    $.ajax({
        url: "http://localhost:8080/team/add",
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
};

function deleteTeam(){
    var id = $("#deleteId").val();
    $.ajax({
        url: "http://localhost:8080/team/delete/" + id,
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

function deleteAllTeams(){
    var confirmation = confirm("Are you sure you want to delete all teams?");
    if(confirmation == true)
    {
    $.ajax({
        url: "http://localhost:8080/team/deleteAll",
        type: "DELETE",
        contentType: "application/json",
        success: function (response) {
            window.location.reload();
        }
    });
    }
}

function editTeam(){
    var id = $("#editId").val();
    var name = $("#editName").val();
    var country = $("#editCountry").val();

    var data = {
        id: id,
        name: name,
        country: country
    }

    $.ajax({
        url: "http://localhost:8080/team/update/" + id,
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