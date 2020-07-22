package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;
    private Session session;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
//        userDaoJDBC.createUsersTable();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String queryCreateTable = "CREATE TABLE user " +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "last_name VARCHAR(50), " +
                "age INTEGER)";
        session.createSQLQuery(queryCreateTable).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        String queryDropTable = "DROP TABLE IF EXISTS user";
        session.createSQLQuery(queryDropTable).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        // language = hql
        Query<User> query = sessionFactory.openSession().createQuery("from User", User.class);
        List<User> users = query.list();
        session.close();
        System.out.println(users.size());
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Session getSession() {
        return session;
    }
}
