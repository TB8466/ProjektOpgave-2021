package wishlist.DatabaseAccessLayer;

import wishlist.Model.User;
import wishlist.Model.Wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishManager {

    //When a new wish is created. Establish connection and insert values
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
    //Receives data from mySQL and insert the data into an ArrayList
    public ArrayList<Wish> viewWishlist(User user) throws SQLException {

            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM Wishes WHERE user_id=?;";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();


            ArrayList<Wish> wishlist = new ArrayList<>();
            while(rs.next()){
                    Wish wish = new Wish(rs.getString("name"),rs.getString("price"),rs.getString("url"));
                    wishlist.add(wish);
            }
            //Return the list, so it can be transferred to view
        return wishlist;
    }
}