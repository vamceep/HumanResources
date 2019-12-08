package org.iiitb.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.iiitb.bean.KeysVO;
import org.iiitb.service.DBConnect;
import org.iiitb.service.KeysService;

import java.util.List;

public class KeysServiceImpl implements KeysService {
    @Override
    public List<KeysVO> getKeys() {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from KeysVO", KeysVO.class);

        List<KeysVO> l = (List<KeysVO>)q.list();
        session.close();
        return l;
    }

    @Override
    public KeysVO getKey(Integer id) {
        if(id == null) return null;
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from KeysVO where myKey=:id", KeysVO.class);
        q.setParameter("id", id);
        KeysVO val = (KeysVO)q.uniqueResult();
        session.close();
        return val;
    }

    public boolean update(KeysVO keyVO) {
        Session session = DBConnect.getSession();
        Transaction t = session.beginTransaction();
        session.update(keyVO);
        t.commit();
        session.close();
        return true;
    }
}
