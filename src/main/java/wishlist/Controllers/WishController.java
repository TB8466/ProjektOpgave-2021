package wishlist.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import wishlist.Users.User;
import wishlist.Users.UserManager;
import wishlist.Wishes.Wish;
import wishlist.Wishes.WishManager;
import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class WishController {

    private UserManager userManager = new UserManager();
    private WishManager wishManager = new WishManager();

    @GetMapping("/")
    public String renderHome(){
        return "home";
    }

    @PostMapping("/login")
    public String renderLogin(){ return "login"; }

    @PostMapping("/newUser")
    public String renderUserCreation(){ return "newUser"; }

    @PostMapping("/register")
    public String createUser(WebRequest request) throws SQLException {
        //Retrieve data from HTML form
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if(password1.equals(password2)){
            User user = new User(username,password1);
            userManager.createUser(user);
            return "home";
        } else{
            return "ERROR";
        }
    }

    @PostMapping("/userHome")
    public String renderUserHome(WebRequest request) throws LoginException, SQLException {
        //Retrieve data from HTML form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       User user = userManager.login(username,password);
        setSessionInfo(request, user);

        return "wishsite";

    }

    @PostMapping("/wishCreator")
    public String renderWishCreator(){
        return "wishCreator";
    }

    @PostMapping("/createWish")
    public String createWish(WebRequest request) throws SQLException {
        //Retrieve data from HTML form
        String name = request.getParameter("wishname");
        String price = request.getParameter("wishprice");
        String url = request.getParameter("wishlink");


        Wish wish = new Wish(name, price, url);
        User user = (User) request.getAttribute("user",WebRequest.SCOPE_SESSION);
        wishManager.createWish(wish, user);

        return "wishsite";
    }

    @PostMapping("/renderWishlist")
    public String renderWishlist(WebRequest request, Model model) throws SQLException {

        User user = (User) request.getAttribute("user",WebRequest.SCOPE_SESSION);

        model.addAttribute("wishlist",wishManager.viewWishlist(user));

        return "wishlistdisplay";
    }

    private void setSessionInfo(WebRequest request, User user) {
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }
}