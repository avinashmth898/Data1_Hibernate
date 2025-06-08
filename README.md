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