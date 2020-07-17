package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String user = "root";
    private static String pass = "Ksusha16.08";
    private static String timeZone = "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";

    private static Connection connection = null;
    private static Statement statement = null;

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/user_sevice(1.1.3)" + timeZone, user, pass);
        return connection;
    }

    public static Statement getStatement() throws SQLException {
        statement = connection.createStatement();
        return statement;
    }
}
