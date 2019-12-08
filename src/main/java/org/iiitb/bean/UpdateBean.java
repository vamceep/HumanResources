package org.iiitb.bean;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

public class UpdateBean {
    private String old_EmpId;

    public String getOld_EmpId() {
        return old_EmpId;
    }

    public void setOld_EmpId(String old_EmpId) {
        this.old_EmpId = old_EmpId;
    }

    private Integer new_EmpId;
    private String name;
    private Integer dept_Id;
    private InputStream photo;
    private FormDataContentDisposition fileDetails;

    public Integer getNew_EmpId() {
        return new_EmpId;
    }

    public void setNew_EmpId(Integer new_EmpId) {
        this.new_EmpId = new_EmpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDept_Id() {
        return dept_Id;
    }

    public void setDept_Id(Integer dept_Id) {
        this.dept_Id = dept_Id;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public FormDataContentDisposition getFileDetails() {
        return fileDetails;
    }

    public void setFileDetails(FormDataContentDisposition fileDetails) {
        this.fileDetails = fileDetails;
    }
}
