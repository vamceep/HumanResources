package org.iiitb.service.impl;

import org.iiitb.bean.Department;
import org.iiitb.bean.Employee;
import org.iiitb.bean.KeysVO;
import org.iiitb.bean.UpdateBean;
import org.iiitb.service.DepartmentService;
import org.iiitb.service.EmployeeService;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.iiitb.service.FileService;
import org.iiitb.service.KeysService;
import org.iiitb.util.BusinessException;

import java.io.InputStream;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee find(Integer id) {
        return employeeDao.find(id);
    }

    @Override
    public List<Employee> findAll() { return employeeDao.findAll();}

    @Override
    public Employee findByEmployeeId(String eid) {
        return employeeDao.findByEmployeeId(eid);
    }

    @Override
    public boolean save(Employee employee, InputStream photo, FormDataContentDisposition fileDetails, Integer departmentId) throws BusinessException {
        Department dept = departmentService.find(departmentId);
        if(dept.getVacancy() <=0) {
            System.out.println("No vacancy");
            throw  new BusinessException("No vacancy in the department.");
        }

        dept.setVacancy(dept.getVacancy()-1);
        departmentService.update(dept);
        employee.setDepartment(dept);

        KeysVO keyVO = generateEmployeeId(dept);
        if(keyVO == null) {
            throw  new BusinessException("All 1000 keys are taken.");
        }
        String eid = convert(keyVO.getMyKey(), dept.getCode());
        employee.setEid(eid);

        String fileName = eid + getExtension(fileDetails.getFileName());
        if (fileService.upload(photo, fileName)) {
            employee.setPhoto(fileName);
            employeeDao.save(employee);
            keyVO.setUsed(1);
            KeysService.keysServiceImpl.update(keyVO);
            return true;
        }
        System.out.println("Error uploading the file");
        throw  new BusinessException("File upload Error");
    }

    @Override
    public boolean update(UpdateBean form) throws BusinessException {
        Employee old = employeeDao.findByEmployeeId(form.getOld_EmpId());
        String eid = old.getEid();

        KeysVO oldKey = KeysService.keysServiceImpl.getKey(Integer.parseInt(eid.substring(eid.length()-3)));
        KeysVO newKey = KeysService.keysServiceImpl.getKey(form.getNew_EmpId());
        Department dept_new = departmentService.find(form.getDept_Id());
        Department dept_old = old.getDepartment();


        if(dept_new != null)
            old.setDepartment(dept_new);
        if(form.getName().length() > 0)
            old.setName(form.getName());
        if(dept_new.getVacancy() <= 0) {
            System.out.println("No vacancy in new department");
            throw new BusinessException("No vacancy in new department");
        }

        if(newKey == null || newKey.getMyKey() == oldKey.getMyKey())
            newKey = oldKey;
        else
            if(newKey != oldKey && newKey.getUsed() == 1) {
                System.out.println("EmployeeId already taken");
                throw new BusinessException("EmployeeId already taken");
            }
        String id = convert(newKey.getMyKey(), dept_new.getCode());
        old.setEid(id);

        if(form.getPhoto() != null) {
            String fileName = "Z"+old.getEid() + getExtension(form.getFileDetails().getFileName());
            if (fileService.upload(form.getPhoto(), fileName)) {
                fileService.deletePhoto(old.getPhoto());
                FileService.fileService.rename(fileName);
                old.setPhoto(fileName.substring(1));
            }
            else {
                FileService.fileService.deletePhoto("Z"+old.getEid());
                System.out.println("File upload Error");
//                throw  new BusinessException("File upload Error");
            }
        }

        employeeDao.update(old);
        oldKey.setUsed(0);
        newKey.setUsed(1);
        KeysService.keysServiceImpl.update(oldKey);
        KeysService.keysServiceImpl.update(newKey);
        if(dept_new != dept_old && !dept_new.getCode().equals(dept_old.getCode())) {
            dept_old.setVacancy(dept_old.getVacancy() + 1);
            dept_new.setVacancy(dept_new.getVacancy() - 1);
            DepartmentService.departmentDao.update(dept_old);
            DepartmentService.departmentDao.update(dept_new);
        }
        return true;
    }

    private KeysVO generateEmployeeId(Department department) {
        KeysService ks = KeysService.keysServiceImpl;
        List<KeysVO> ll = ks.getKeys();
        for(int i = 0; i < ll.size(); i++) {
            if(ll.get(i).getUsed() == 0) {
                return ll.get(i);
            }
        }
        System.out.println("All Keys Used");
        return null;
    }

    private String convert(Integer key, String code) {
        String eid = key.toString();
        if(eid.length() < 2) {
            eid = "00" + eid;
        }
        else if(eid.length() == 2) {
            eid = "0" + eid;
        }
        return code+eid;
    }

    private String getExtension(String filName) {
        String exten = filName;
        int lastIndex = exten.lastIndexOf('.');
        if(lastIndex > 0 ) exten = exten.substring(lastIndex);
        else exten = "";
        return (exten);
    }

}