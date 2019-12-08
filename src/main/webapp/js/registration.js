var api1 = "HR/department";
$.get(api1, function (dept, status) {
    if (status == "success") {
        var val = "";
        for (var i = 0; i < dept.length; i++) {
            val += '<option value="' + dept[i].id + '">' + dept[i].name + '</option>';
        }
        $("#departmentId").append(val);
    }
});

console.log(123);

var api2 = "HR/register/getAll";
$.get(api2, function (emp, status) {
    if (status == "success") {
        var val = "";
        for (var i = 0; i < emp.length; i++) {
            val += '<option val="' + emp[i].id + '">' + emp[i].eid + '</option>';
        }
        $("#employeeId").append(val);
        $("#employeeId").val(0);
        $("#employeeId").selectpicker("refresh");
    }
});

function fillDetails(var1) {
    var api3 = "HR/register/getEmployee";
    var emp;
    $.ajax({
        type: "GET",
        url: api3,
        data: "eid="+$("#employeeId").val(),
        success: function( data ) {
            $("#name").val(data.name);
            $("#new_empid").attr("hidden", false);
            $("#new_empid").attr("autofocus", true);
            $("#new_empid").val(data.eid.substring(data.eid.length-3));
            $("#departmentId").val("0");
            $("#departmentId").val(data.department.id);
            $("#photograph").attr("required",false);
            var path = "images/employee/"+data.photo;
            document.getElementById("preview").src = path;
            console.log(data);
        },
        error : function() {
            $("#new_empid").val("");
            $("#new_empid").attr("hidden",true);
            $("#name").val("");
            $("#departmentId").val("0");
            $("#photograph").attr("required",true);
            document.getElementById("preview").src = "images/icons/placeholder.png"
        }
    });
}

function previewFile() {
    var preview = document.getElementById('preview');
    var fileName = $(".custom-file-label");
    var file = document.querySelector('input[type=file]').files[0]; //sames as here
    var reader = new FileReader();

    reader.onloadend = function () {
        preview.src = reader.result;
        fileName.html(file.name);
    };

    if (file) {
        reader.readAsDataURL(file); //reads the data as a URL
        console.log(file);
    } else {
        preview.src = "images/placeholder.png";
        fileName.html("Photograph");
    }
}