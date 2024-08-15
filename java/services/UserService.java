package services;

import java.sql.SQLException;

import dao.UserDAO;
import model.User;

public class UserService {
    public static Integer saveUser(User user) {
        try {
            if (UserDAO.isExists(user.getEmail())) {
                return 1; // User already exists
            } else {
                return UserDAO.saveUser(user) > 0 ? 0 : -1; // Return -1 if saveUser fails
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return -1; // Indicate failure
        }
    }
}
