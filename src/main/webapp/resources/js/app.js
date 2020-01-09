$(document).ready(function() {
    $('table.display').DataTable();
} );

function sortTable(n, tableName) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById(tableName);
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
     no switching has been done:*/
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /*Loop through all table rows (except the
         first, which contains table headers):*/
        for (i = 1; i < (rows.length - 1); i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /*Get the two elements you want to compare,
             one from current row and one from the next:*/
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*check if the two rows should switch place,
             based on the direction, asc or desc:*/
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch= true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
             and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            //Each time a switch is done, increase this count by 1:
            switchcount ++;
        } else {
            /*If no switching has been done AND the direction is "asc",
             set the direction to "desc" and run the while loop again.*/
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function showCurrency(element) {
    if (element.value == 'UAH'){
        document.getElementById('UAH').style.display = 'block';
        document.getElementById('NOK').style.display = 'none';
        document.getElementById('USD').style.display = 'none';
        document.getElementById('EUR').style.display = 'none';
        document.getElementById('RUB').style.display = 'none';
    }

    if (element.value == 'NOK'){
        document.getElementById('NOK').style.display = 'block';
        document.getElementById('UAH').style.display = 'none';
        document.getElementById('USD').style.display = 'none';
        document.getElementById('EUR').style.display = 'none';
        document.getElementById('RUB').style.display = 'none';
    }

    if (element.value == 'USD'){
        document.getElementById('USD').style.display = 'block';
        document.getElementById('NOK').style.display = 'none';
        document.getElementById('UAH').style.display = 'none';
        document.getElementById('EUR').style.display = 'none';
        document.getElementById('RUB').style.display = 'none';
    }

    if (element.value == 'EUR'){
        document.getElementById('EUR').style.display = 'block';
        document.getElementById('NOK').style.display = 'none';
        document.getElementById('USD').style.display = 'none';
        document.getElementById('UAH').style.display = 'none';
        document.getElementById('RUB').style.display = 'none';
    }

    if (element.value == 'RUB'){
        document.getElementById('RUB').style.display = 'block';
        document.getElementById('NOK').style.display = 'none';
        document.getElementById('USD').style.display = 'none';
        document.getElementById('EUR').style.display = 'none';
        document.getElementById('UAH').style.display = 'none';
    }
}

function setLink(element) {
    var action = element.value == "csv" ? "csv" : "excel";
    if (element.id == "exportUsersSelect") {
        document.getElementById('exportUsers').setAttribute("action","/reports/users/" + action);
    } else if (element.id == "exportMembersSelect") {
        document.getElementById('exportMembers').setAttribute("action","/reports/members/" + action);
    } else if (element.id == "exportBeneficiariesSelect") {
        document.getElementById('exportBeneficiaries').setAttribute("action","/reports/beneficiaries/" + action);
    } else if (element.id == "exportProjectsSelect") {
        document.getElementById('exportProjects').setAttribute("action","/reports/projects/" + action);
    } else if (element.id == "exportProjectMembersSelect") {
        document.getElementById('exportProjectMembers').setAttribute("action","/reports/project/members/" + action);
    } else if (element.id == "exportTransactionsSelect") {
        document.getElementById('exportTransactions').setAttribute("action","/reports/transactions/" + action);
    }
}

function showUserFields(element) {
    if (element.value == 0){
        element.value = 1;
        document.getElementById('userFieldsDiv').style.display = 'block';
        document.getElementById('excelOptionUser').hidden = true;
        document.getElementById('csvOptionUser').selected = true;

    } else {
        element.value = 0;
        document.getElementById('userFieldsDiv').style.display = 'none';
        document.getElementById('excelOptionUser').hidden = false;
    }
}