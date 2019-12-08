package org.iiitb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.iiitb.bean.Department;
import org.iiitb.bean.Employee;
import org.iiitb.service.DBConnect;

import java.util.List;

public class EmployeeDao{

    public List<Employee> findAll() {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from Employee", Employee.class);
        List<Employee> ll = (List<Employee>)q.list();
        session.close();
        return ll;
    }

    public Employee findByEmployeeId(String eids) {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from Employee where eid = :eid_s", Employee.class);
        q.setParameter("eid_s", eids);
        Employee emp = (Employee)q.uniqueResult();

        Transaction t = session.beginTransaction();
        t.commit();
        session.close();
        return emp;
    }

    public Employee find(Integer id) {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from Employee where id = :id", Employee.class);
        q.setParameter("id", id);
        Employee emp = (Employee)q.uniqueResult();

        Transaction t = session.beginTransaction();
        t.commit();
        session.close();
        return emp;
    }

    public boolean update(Employee edit) {
        Session session = DBConnect.getSession();
        Transaction t = session.beginTransaction();
        session.update(edit);
        t.commit();
        session.close();
        return true;
    }

    public void save(Employee employee) {
        Session session = DBConnect.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }

    public String getLastEmployeeId(Department department) {
        Session session = DBConnect.getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT e.eid FROM Employee e, Department d WHERE e.department = d AND d = :department ORDER BY e.eid DESC";
        Query query = session.createQuery(hql);
        query.setParameter("department", department);
        List<String> l = query.list();
        transaction.commit();
        session.close();
        String empId = l.size()==0?"":l.get(0);
        return empId;
    }
}
