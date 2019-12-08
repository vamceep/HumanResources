package org.iiitb.controller;

import com.google.gson.Gson;
import org.iiitb.bean.Department;
import org.iiitb.service.DepartmentService;
import org.iiitb.service.impl.DepartmentServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/department")
public class DepartmentController {
    private DepartmentService domainService = new DepartmentServiceImpl();

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listDomains() {
        List<Department> departments = domainService.findAll();

        Gson gson = new Gson();
        return Response.ok(gson.toJson(departments),MediaType.APPLICATION_JSON).build();
    }
}
