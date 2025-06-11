package com.learn.hiber.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory; // creating a variable of SessionFactory
    static {
        try {
            if(sessionFactory==null)/*Since you need to create SessionFactory once*/{
                sessionFactory=new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
//      with the above variable here building SessionFactory where Configuration says that configure the file
//                with resource name "Hibernate.cfg.xml" and then build now.
            }

        } catch (Exception e) {
            throw new RuntimeException("Error in creating Session Factory"+e.getMessage());
        }
    }
    public static SessionFactory getSessionFactory(){//this function is a getter of SessionFactory since build has been successfully
//        done in the static block
        return sessionFactory;
    }
}
