package org.iiitb.service;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.iiitb.bean.Employee;
import org.iiitb.bean.UpdateBean;
import org.iiitb.dao.EmployeeDao;
import org.iiitb.service.impl.DepartmentServiceImpl;
import org.iiitb.service.impl.FileServiceImpl;
import org.iiitb.util.BusinessException;

import java.io.InputStream;
import java.util.List;

public interface EmployeeService {

    EmployeeDao employeeDao = new EmployeeDao();
    DepartmentService departmentService = new DepartmentServiceImpl();
    FileService fileService = new FileServiceImpl();

    boolean save(Employee enployee, InputStream photo, FormDataContentDisposition fileDetails,Integer departmentId) throws BusinessException;

    //boolean update(Employee newEmp,Integer oldEmpId, InputStream photo, FormDataContentDisposition fileDetails,Integer departmentId);

    boolean update(UpdateBean form) throws BusinessException;

    Employee find(Integer id);

    List<Employee> findAll();

    Employee findByEmployeeId(String eid);


}
