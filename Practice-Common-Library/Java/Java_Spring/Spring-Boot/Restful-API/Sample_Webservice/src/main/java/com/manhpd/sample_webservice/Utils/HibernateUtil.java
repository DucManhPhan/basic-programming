/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.sample_webservice.Utils;

import org.hibernate.cfg.Configuration;
import com.manhpd.sample_webservice.models.Employee;
import java.util.Properties;
import org.hibernate.SessionFactory;

/**
 *
 * @author manh.phanduc
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Properties prop = new Properties();
            prop.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            prop.put("hibernate.connection.url", "jdbc:mysql://ip-computer:port/mydb");
            prop.put("hibernate.connection.username", "root");
            prop.put("hibernate.connection.password", "password");
            prop.put("hibernate.current_session_context_class", "thread");   
            prop.put("hibernate.query.factory_class", "org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory");
            prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            prop.put("hibernate.show_sql", "true");
            
            sessionFactory = new Configuration()
                    .addPackage("com.manhpd.sample_webservice.models")
                    .addProperties(prop)
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
             // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
