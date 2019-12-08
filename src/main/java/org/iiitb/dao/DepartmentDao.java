package org.iiitb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.iiitb.bean.Department;
import org.iiitb.service.DBConnect;

import java.util.List;

public class DepartmentDao {

    public List<Department> findAll() {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from Department", Department.class);
        List<Department> ll = q.list();
        for(int i = 0;i < ll.size(); i++) {
            ll.get(i).setEmployees(null);
        }

        Transaction t = session.beginTransaction();
        t.commit();
        session.close();
        return ll;
    }

    public Department find(Integer id) {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from Department where id = :id",Department.class);
        q.setParameter("id",id);
        Department d = (Department)q.uniqueResult();

        Transaction t = session.beginTransaction();
        t.commit();
        session.close();
        return d;
    }

    public Department findByCode(String code) {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from Department where code = :code",Department.class);
        q.setParameter("code",code);
        Department d = (Department)q.uniqueResult();

        Transaction t = session.beginTransaction();
        t.commit();
        session.close();
        return d;
    }

    public boolean save(Department d) {
        d.setVacancy(d.getCapacity());
        Session session = DBConnect.getSession();
        Transaction t = session.beginTransaction();
        session.save(d);
        t.commit();
        session.close();
        return true;
    }



    public boolean update(Department d) {
        Session session = DBConnect.getSession();
        Transaction t = session.beginTransaction();
        session.update(d);
        t.commit();
        session.close();
        return true;
    }
}