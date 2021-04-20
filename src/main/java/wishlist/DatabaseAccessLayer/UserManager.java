package wishlist.DatabaseAccessLayer;

import wishlist.Model.User;

import javax.security.auth.login.LoginException;
import java.sql.*;

public class UserManager {

    //When a new user is created. Establish connection and insert values
    public void createUser(User user) throws SQLException {
        Connection con = DBManager.getConnection();
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        //Prepare statements and insert them into SQL
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.executeUpdate();
    }

    //When a user logs in. Verify users existence
    public User login(String username, String password) throws SQLException, LoginException {
        Connection con = DBManager.getConnection();
        String query = "SELECT id FROM Users WHERE username=? AND password=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        //Creates a temporary User object and verifies if that user exists within mySQL
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