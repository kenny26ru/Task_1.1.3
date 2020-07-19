package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = null;
    private Statement statement = null;
    private final List<User> listUser = new ArrayList<>();

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getConnection();
            statement = Util.getStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        String queryCreateTable = "CREATE TABLE user " +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "last_name VARCHAR(50), " +
                "age INTEGER)";
        try {
            statement.executeUpdate(queryCreateTable);
        } catch (SQLException t) {
            System.out.println("Table is created");
        }
    }

    public void dropUsersTable() {
        String queryDropTable = "DROP TABLE user";
        try {
            statement.executeUpdate(queryDropTable);
        } catch (SQLException t) {
            System.out.println("Table is dropped");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.executeUpdate("INSERT INTO user (name, last_name, age) " +
                    "VALUES ('" + name + "', '" + lastName + "','" + age + "')");
            listUser.add(new User(name, lastName, age));
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String queryDelete = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
//            listUser.remove(listUser.get());
            System.out.println("User is deleted");
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return listUser;
    }

    public void cleanUsersTable() {
        String queryTruncateTable = "TRUNCATE user";
        try {
            statement.executeUpdate(queryTruncateTable);
            listUser.clear();
            System.out.println("User table is clean");
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
