package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MyConnection;
import model.User;

public class UserDAO {
    public static boolean isExists(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement("select email from users where email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) MyConnection.closeConnection();
        }
    }

    public static int saveUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement("insert into users (name, email) values(?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (connection != null) MyConnection.closeConnection();
        }
    }
}
