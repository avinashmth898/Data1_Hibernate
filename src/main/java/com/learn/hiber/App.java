package com.learn.hiber;

import com.learn.hiber.entities.Company;
import com.learn.hiber.entities.Customer;
import com.learn.hiber.entities.Product;
import com.learn.hiber.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.print("Hello and welcome!");
//        Customer customer = new Customer("Anil Kumar", "9521621023", 4.5, "Delhi", true );
//        Customer customer1 = new Customer("Avinash Kumar", "9430587463", 4.5, "Delhi", true );
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); // Create a session factory instance that you've already
//        defined in the package "com.learn.hiber.util.HibernateUtil.java";
//        you've defined it in such a way that if you'll create an instance of it it'll open a session and once sessionFactory is created
//        You don't need to create it again.
        Product product1= new Product();
        Product product2= new Product();
        Product product3= new Product();
        Product product4= new Product();
        product2.setName("NPK");
        product2.setPrice(1650.50);
        product1.setName("Urea");
        product1.setPrice(266.89);
        product3.setName("DAP");
        product3.setPrice(1350.90);
        product4.setName("MOP");
        product4.setPrice(1850.00);
//        product1.setProduct_id(1);
        Company company1= new Company();
        company1.setName("HURL");
        Company company2 = new Company();
        company1.setName("NFL");

        company1.addProduct(product1);
        company1.addProduct(product2);
        company1.addProduct(product3);
        company1.addProduct(product4);

        company2.addProduct(product1);
        company2.addProduct(product2);
        company2.addProduct(product3);
        company2.addProduct(product4);

        System.out.println(sessionFactory);
        Session session = sessionFactory.openSession(); // Open a current session and then do transaction within that
        Transaction transaction = null; // Transaction will be called everytime while having interaction with the database;
        try {
            transaction=session.beginTransaction(); // this will flag the transaction incoming
//            session.persist(customer); // Save the object locally
//            session.persist(customer1);
            session.persist(product1);
            session.persist(product2);
            session.persist(product3);
            session.persist(product4);
            session.persist(company1);
            session.persist(company2);
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