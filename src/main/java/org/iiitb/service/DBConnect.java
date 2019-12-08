package org.iiitb.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.iiitb.bean.Department;
import org.iiitb.bean.Employee;
import org.iiitb.bean.KeysVO;



public class DBConnect {
    static DBConnect dbc;
    static Configuration conf;
    static SessionFactory sf;


    static {
        conf = new Configuration();
        conf.addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class).addAnnotatedClass(KeysVO.class);
        sf = conf.buildSessionFactory();
    }

    private DBConnect() {
        if(conf == null || sf == null) {
            dbc = this;
            conf = new Configuration();
            conf.addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class);
            sf = conf.buildSessionFactory();
        }
    }

    public static Session getSession() {
        if(sf == null) new DBConnect();
        return sf.openSession();
    }
}