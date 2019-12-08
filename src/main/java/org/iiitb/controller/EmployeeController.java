package org.iiitb.controller;

import com.google.gson.Gson;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import org.iiitb.bean.Employee;
import org.iiitb.bean.UpdateBean;
import org.iiitb.service.EmployeeService;
import org.iiitb.service.impl.EmployeeServiceImpl;
import org.iiitb.util.BusinessException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("/register")
public class EmployeeController {
    private EmployeeService empService = new EmployeeServiceImpl();

    @Path("/")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String addEmployee(@FormDataParam("employeeId")String old_Id,
                              @FormDataParam("new_empid") Integer new_Id,
                              @FormDataParam("name") String name,
                              @FormDataParam("departmentId") Integer deptId,
                              @FormDataParam("photograph") InputStream photo,
                              @FormDataParam("photograph") FormDataContentDisposition fileDetails
                             ) {
        String err_message = "";
        boolean status;

        if(old_Id.equals("0")) {
            Employee emp = new Employee();
            emp.setName(name);
            try {
                status = empService.save(emp, photo, fileDetails, deptId);
            } catch (BusinessException e) {
                e.printStackTrace();
                err_message = e.getMessage();
                status = false;
            }
        }
        else {
            UpdateBean form = new UpdateBean();
            Integer id = Integer.parseInt(old_Id.substring(old_Id.length()-3));
            form.setOld_EmpId(old_Id);
            form.setNew_EmpId(new_Id);
            form.setName(name);
            form.setDept_Id(deptId);
            form.setPhoto(photo);
            form.setFileDetails(fileDetails);

            try {
                status = empService.update(form);
            } catch (BusinessException e) {
                e.printStackTrace();
                err_message = e.getMessage();
                status = false;
            }
        }
        return status? "Done": err_message;
    }

    @Path("/getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        Gson gson = new Gson();
        EmployeeService es = new EmployeeServiceImpl();
        List<Employee> l = es.findAll();
        for(int i= 0; i < l.size(); i++) {
            l.get(i).setDepartment(null);
        }
        return Response.ok(gson.toJson(l),MediaType.APPLICATION_JSON).build();
    }


    @Path("/getEmployee")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmployee(@QueryParam("eid") String eid) {
        Gson gson = new Gson();
        EmployeeService es = new EmployeeServiceImpl();
        Employee e = es.findByEmployeeId(eid);
        e.getDepartment().setEmployees(null);
        return Response.ok(gson.toJson(e),MediaType.APPLICATION_JSON).build();
    }

}