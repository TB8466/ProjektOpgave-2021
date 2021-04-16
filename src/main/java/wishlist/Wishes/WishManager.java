package wishlist.Wishes;

import wishlist.Database.DBManager;
import wishlist.Users.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Wish> viewWishlist(User user) throws SQLException {

            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM wishes WHERE user_id=1;";
            PreparedStatement ps = con.prepareStatement(query);

            //ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            ArrayList<Wish> wishlist = new ArrayList<>();
            while(rs.next()){
                    Wish wish = new Wish(rs.getString("name"),rs.getString("price"),rs.getString("url"));
                    wishlist.add(wish);
            }

            return wishlist;
    }
}