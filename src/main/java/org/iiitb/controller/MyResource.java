package org.iiitb.controller;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.iiitb.bean.Department;
import org.iiitb.bean.KeysVO;
import org.iiitb.service.DBConnect;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/my")
public class MyResource {
    @POST
    @Path("/all")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@FormDataParam("name") String a) {
        return a;
    }

    public static void main(String[] arg) {
//        Session session = DBConnect.getSession();
//        Transaction t = session.beginTransaction();
//        for(int i = 0; i < 1000; i++){
//            KeysVO keysVO = new KeysVO();
//            keysVO.setUsed(0);
//            keysVO.setMyKey(i);
//            session.save(keysVO);
//        }

//        Department[] ary = new Department[5];
//        for(int i = 0; i < 5; i++) {
//            ary[i] = new Department();
//            ary[i].setCode("d");
//            ary[i].setCapacity(5);
//            ary[i].setVacancy(5);
//            ary[i].setName(ary[i].getCode()+ary[i].getId());
//            session.save(ary[i]);
//        }
//        t.commit();
//        session.close();
    }
}
