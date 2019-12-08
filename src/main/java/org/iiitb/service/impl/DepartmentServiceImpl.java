package org.iiitb.service.impl;

import org.iiitb.bean.Department;
import org.iiitb.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    public Department find(Integer id) {
        return departmentDao.find(id);
    }

    @Override
    public Department findByCode(String code) {
        return departmentDao.findByCode(code);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public boolean update(Department d) {
        return departmentDao.update(d);
    }
}
