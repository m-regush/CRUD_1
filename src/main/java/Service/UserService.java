package Service;

import DAO.UserDAO;
import Model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<User> getAllUser() {
        try {
            if (!(getUserDAO().getAllUser().isEmpty())) {
                return getUserDAO().getAllUser();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public User getClientByName(String name) {
        try {
            return getUserDAO().getUserByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getClientById(Long id) throws SQLException {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public boolean addUser(User user) throws SQLException {
        if (getUserDAO().getUserByName(user.getName()) == null) {
            getUserDAO().addUser(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String name) {
        return getUserDAO().deleteUser(name);
    }

    public boolean updateUser(User user) throws SQLException {
        if (getUserDAO().getUserById(user.getId()) != null) {
            getUserDAO().updateUser(user);
            return true;
        }
        return false;
    }

    public void createTable() {
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();


            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            System.out.println(connection);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
