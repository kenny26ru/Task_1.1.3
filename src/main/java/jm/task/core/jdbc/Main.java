package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // реализуйте алгоритм здесь
//        UserServiceImpl userService = new UserServiceImpl();
//        System.out.println("DB loaded access!");
//        userService.createUsersTable();
//        userService.saveUser("David", "Bordakevich", (byte) 20);
//        userService.saveUser("Jo", "Pino", (byte) 43);
//        userService.saveUser("Shrek", "Rulo", (byte) 23);
//        userService.saveUser("Ralf", "Pushko", (byte) 55);
//
//        List<User> list = userService.getAllUsers();
//        for (User user : list) {
//            System.out.println(user.toString());
//        }
//        userService.removeUserById(2);
//        userService.removeUserById(3);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
//        userService.close();

        UserDaoHibernateImpl udh = new UserDaoHibernateImpl();
        udh.getSession().beginTransaction();
        udh.saveUser("Cris", "Nolan", (byte) 43);
        udh.saveUser("Bro", "Sultan", (byte) 50);
        List<User> list = udh.getAllUsers();
        System.out.println(list.size());
        udh.getSession().getTransaction().commit();
        udh.getSession().close();
    }
}
