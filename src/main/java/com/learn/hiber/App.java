package com.learn.hiber;

import com.learn.hiber.entities.Customer;
import com.learn.hiber.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.print("Hello and welcome!");
        Customer customer = new Customer("Anil Kumar", "9521621023", 4.5, "Delhi", true );
//
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); // Create a session factory instance that you've already
//        defined in the package "com.learn.hiber.util.HibernateUtil.java";
//        you've defined it in such a way that if you'll create an instance of it it'll open a session and once sessionFactory is created
//        You don't need to create it again.

//        System.out.println(sessionFactory);
        Session session = sessionFactory.openSession(); // Open a current session and then do transaction within that
        Transaction transaction = null; // Transaction will be called everytime while having interaction with the database;
        try {
            transaction=session.beginTransaction(); // this will flag the transaction incoming
            session.persist(customer); // Save the object locally
            transaction.commit();// This will save the data into database;
            System.out.println("Customer saved successfully ");

        } catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
            }
                e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}