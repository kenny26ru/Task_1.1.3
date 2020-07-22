package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // Настройка соеденения с БД JDBC
    private static String user = "root";
    private static String pass = "Ksusha16.08";
    private static String timeZone = "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";

    private static Connection connection = null;
    private static Statement statement = null;

    // Настройка Hibernate
    private static SessionFactory sessionFactory = null ;
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory configurationSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3307/user_sevice(1.1.3)" + timeZone)
                .setProperty("hibernate.connection.username", user)
                .setProperty("hibernate.connection.password", pass)
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("show_sql", "true")
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        configurationSessionFactory();
        return sessionFactory;
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/user_sevice(1.1.3)" + timeZone, user, pass);
        return connection;
    }

    public static Statement getStatement() throws SQLException {
        statement = connection.createStatement();
        return statement;
    }
}
