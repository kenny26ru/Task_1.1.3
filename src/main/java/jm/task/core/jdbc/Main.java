package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println("DB loaded access!");
        userService.createUsersTable();
        userService.saveUser("David", "Bordakevich", (byte) 20);
        userService.saveUser("Jo", "Pino", (byte) 43);
        userService.saveUser("Shrek", "Rulo", (byte) 23);
        userService.saveUser("Ralf", "Pushko", (byte) 55);

        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user.toString());
        }
        userService.removeUserById(2);
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.close();
    }
}
