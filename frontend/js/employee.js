getAllEmployees();
function saveEmployee(){
    let name =$('#empName').val();
    let address =$('#empAddress').val();
    let number =$('#empNumber').val();

    $.ajax({
        method: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/v1/employee/saveEmployee',
        async:true,
        data: JSON.stringify({
            "empId":"",
            "empName":name,
            "empAddress":address,
            "empNumber":number
        }),
        success: function(data){
            alert("save");
            getAllEmployees();
        },
        error: function(xhr,exception){
            alert("error");
        }

    })

}
function updateEmployee(){
    let id =$('#empId').val();
    let name =$('#empName').val();
    let address =$('#empAddress').val();
    let number =$('#empNumber').val();

    $.ajax({
        method: 'PUT',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/v1/employee/updateEmployee',
        async:true,
        data: JSON.stringify({
            "empId":id,
            "empName":name,
            "empAddress":address,
            "empNumber":number
        }),
        success: function(data){
            alert("Updated");
            getAllEmployees();
        },
        error: function(xhr,exception){
            alert("error");
        }

    })

}

function deleteEmployee(){
    let id =$('#empId').val();

    $.ajax({
        method: 'DELETE',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/v1/employee/deleteEmployeeById/'+id,
        async:true,
        success: function(data){
            alert("Deleted");
            getAllEmployees();
        },
        error: function(xhr,exception){
            alert("error");
        }

    })

}
function getAllEmployees(){
    let id =$('#empId').val();

    $.ajax({
        method: 'GET',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/v1/employee/getAllEmployees',
        async:true,
        success: function(data){
            if(data.code==="00"){
               $('#empTable').empty();
               for(let emp of data.content){
                   let id =emp.empId;
                   let name =emp.empName;
                   let address =emp.empAddress;
                   let number =emp.empNumber;
                   let row=`<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${number}</td><td>`;
                   $('#empTable').append(row);
                }
            }
        },
        error: function(xhr,exception){
            alert("error");
        }

    })

}
$(document).ready(function(){
    $(document).on('click','#empTable tr',function (){
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();

        $('#empId').val(col0);
        $('#empName').val(col1);
        $('#empAddress').val(col2);
        $('#empNumber').val(col3);
    });
})
