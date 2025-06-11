1. Create a maven project
2. Add dependencies inside the pom.xml
3. Dependencies like hibernate and my sql
```xml
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>7.0.0.Final</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.2.0</version>
        </dependency>
    </dependencies>
```
4. run "mvn clean install" to install dependencies.

```shell
mvn clean install 
```
5. Create xml configuration file(hibernate.cfg.xml) in resources folder
6. Inside hibernate.cfg.xml Put UTF-8, the TDT content
7. Start a hibernate Session factory and sessions like code below

```xml
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <!-- Database connection settings -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/your_database</property>
        <property name="hibernate.connection.username">your_username</property>
        <property name="hibernate.connection.password">your_password</property>

        <!-- JDBC dialect -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>

        <!-- Show SQL in console -->
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>

        <!-- Automatically create/update tables -->
        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- Mapping class -->
        <mapping class="com.learn.hiber.YourEntityClass"/>
    </session-factory>
</hibernate-configuration>

```
8. The above code is an example
     a. database configuration 
     b. hibernate configuration
     c. xml/class mapping 

9. You can add multiple classes in mapping in case of more than a single table.

Now,

You've created your entity package, It's time to create entities like a customer I've created,

```java
package com.learn.hiber.entities;

import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aadharId;

    @Column(name = "customer_name",length = 50)
    private String name;

    @Column(name = "mobile_number",length=12, unique = true)
    private String mobNo;
    private double land;

    @Lob
    private String address;
    private boolean eligible=true;
}

```

The Annotations are from dependency for hibernate that we've just added in the hibernate.cfg.xml file.
1. @Entity
```java
import jakarta.persistence.Entity;
//to Identify it as an The 
// @Entity annotation in JPA (jakarta.persistence.Entity)
// is used to mark a Java class as a JPA entity — 
// meaning it will be mapped to a database table by the 
// persistence provider (like Hibernate).

@Entity
```

2. @Table

```java
import jakarta.persistence.Table;
//the 
@Table
```

3. Similarly, some important  annotations 

Hibernate/JPA Core Annotations
🧱 Entity and Table Mapping
Annotation	Purpose
```java
@Entity	//Marks the class as a JPA entity (mapped to a database table).
@Table(name = "table_name")	//Specifies the name of the table (optional, default is class name).
```
🔑 Primary Key and Generation
Annotation	Purpose
```java
@Id	//Marks the primary key field. Required in every entity.
@GeneratedValue(strategy = GenerationType.X)	//Automatically generates primary key values. Strategies include AUTO, IDENTITY, SEQUENCE, and TABLE.
```
🧩 Field and Column Mapping
Annotation	Purpose
```java
@Column(name = "column_name")	//Maps a field to a column (optional if field name = column name).
@Transient	//Tells Hibernate to ignore this field (not persisted to the DB).
@Lob	//Used to map large objects like Clob or Blob.
@Enumerated(EnumType.STRING)	//Stores enum as STRING instead of ordinal (number).
```
⏱ Date and Time
Annotation	Purpose
```java
@Temporal(TemporalType.DATE/TIME/TIMESTAMP)	//Specifies the date/time precision for java.util.Date.
```
🤝 Relationships
Annotation	Purpose
```java


@OneToOne	//One-to-one relationship between two entities.
@OneToMany	//One-to-many relationship (mapped as a list/collection).
@ManyToOne	//Many-to-one relationship (foreign key reference).
@ManyToMany	//Many-to-many relationship with a join table.
@JoinColumn(name = "column_name")	//Specifies the foreign key column.
@JoinTable(...)	/*Used with*/ @ManyToMany //to specify the join table and columns.
```
📦 Embedded & Inheritance
Annotation	Purpose
```java
@Embedded	//Embeds a reusable value type class.
@Embeddable	//Marks the reusable class used with @Embedded.
@Inheritance(strategy = InheritanceType.X)	//Defines inheritance strategy (e.g., SINGLE_TABLE, JOINED, TABLE_PER_CLASS).
@DiscriminatorColumn(name = "type")	//Used with inheritance for type differentiation.
```
🔐 Validation & Constraints (Bean Validation API)
Annotation	Purpose
```java
@NotNull, @Size, @Min, @Max	//Validation constraints on entity fields.
@Pattern(regexp = "regex")	//Specifies a regex pattern for the field.
```

```xml
<!--YOUR CLASS NAME GOES AT THE PLACE OF "YourEntityClass"-->
<mapping class="com.learn.hiber.YourEntityClass"/>
<mapping class="com.learn.hiber.YourEntityClass"/>
<mapping class="com.learn.hiber.YourEntityClass"/>
<!--You can of course add multiple classes to the hibernate.cfg.xml file-->
<!--In case you want to create multiple tables in single go.-->
```
**Singleton Object:-**

A Singleton Object is a design pattern in object-oriented programming where only one instance of a class is created and shared throughout the entire application.

So that Object is for **SessionFactory**, 
Now to build a session factory,

```java
//Create a session factory object

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

private static SessionFactory sessionFactory;
//Now Build a session factory
sessionFactory=new Configuration.configure("hibernate.cfg.xml").buildSessionFactory();
```
The Singleton class for SessionFactory is
```java
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

```

Main.java
```java
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
```
the customer class

Customer.java
```java
package com.learn.hiber.entities;

import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aadharId;

    @Column(name = "customer_name",length = 50)
    private String name;

    @Column(name = "mobile_number",length=12, unique = true)
    private String mobNo;
    private double land;

    @Lob
    private String address;
    private boolean eligible=true;

    public void setAadharId(long aadharId){
        this.aadharId=aadharId;
    }
    public long getAadharId(){
        return aadharId;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return getName();
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public double getLand() {
        return land;
    }

    public void setLand(double land) {
        this.land = land;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }
    public Customer( String name, String mobNo, double land, String address, boolean eligible){
        this.name=name;
        this.mobNo=mobNo;
        this.land=land;
        this.address=address;
        this.eligible=eligible;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "aadharId=" + aadharId +
                ", name='" + name + '\'' +
                ", mobNo='" + mobNo + '\'' +
                ", land=" + land +
                ", address='" + address + '\'' +
                ", eligible=" + eligible +
                '}';
    }
}

```