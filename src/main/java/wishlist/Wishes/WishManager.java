package wishlist.Wishes;

import wishlist.Database.DBManager;
import wishlist.Users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WishManager {


    public void createWish(Wish wish, User user) throws SQLException{
        Connection con = DBManager.getConnection();
        String query = "INSERT INTO Wishes (name, price, url, user_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, wish.getName());
        ps.setString(2, wish.getPrice());
        ps.setString(3, wish.getUrl());
        ps.setInt(4,user.getId());
        ps.executeUpdate();
    }
}