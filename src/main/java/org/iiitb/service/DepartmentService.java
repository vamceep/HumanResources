package org.iiitb.service;

import org.iiitb.bean.Department;
import org.iiitb.dao.DepartmentDao;

import java.util.List;

public interface DepartmentService {

    DepartmentDao departmentDao = new DepartmentDao();

    void save(Department department);

    Department find(Integer id);

    Department findByCode(String code);

    List<Department> findAll();

    boolean update(Department d);
}


/*
@GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        DepartmentDao dept_dao = new DepartmentDao();
        List<Department> al = dept_dao.getAllDepartments();
        Gson gson = new Gson();
        return Response.ok(gson.toJson(al), MediaType.APPLICATION_JSON).build();
    }
 */
