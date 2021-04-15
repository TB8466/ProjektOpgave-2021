package wishlist.Users;

import wishlist.Database.DBManager;

import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    //When a new user is created. Establish connection and insert values
    public void createUser(User user) throws SQLException {
        Connection con = DBManager.getConnection();
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.executeUpdate();
    }

    public User login(String username, String password) throws SQLException, LoginException {
        Connection con = DBManager.getConnection();
        String query = "SELECT id FROM Users WHERE username=? AND password=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int id = rs.getInt("id");

            User user = new User(username,password);
            user.setId(id);
            return user;
        }
        else {
            throw new LoginException("USER NOT FOUND");
        }
    }
}