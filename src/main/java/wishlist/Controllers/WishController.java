package wishlist.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import wishlist.Model.User;
import wishlist.DatabaseAccessLayer.UserManager;
import wishlist.Model.Wish;
import wishlist.DatabaseAccessLayer.WishManager;
import javax.security.auth.login.LoginException;
import java.sql.SQLException;

@Controller
public class WishController {

    // Object From DatabaseAccessLayer
    private UserManager userManager = new UserManager();
    private WishManager wishManager = new WishManager();

    //Home
    @GetMapping("/")
    public String renderHome(){
        return "home";
    }
    //LoginMenu
    @PostMapping("/login")
    public String renderLogin(){ return "login"; }
    //Create new user menu
    @PostMapping("/newUser")
    public String renderUserCreation(){ return "newUser"; }
    //Verifies password, creates User-object and sends it to SQL
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
    //Users homepage
    @PostMapping("/userHome")
    public String renderUserHome(WebRequest request) throws LoginException, SQLException {
        //Retrieve data from HTML form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       User user = userManager.login(username,password);
        setSessionInfo(request, user);

        return "wishsite";
    }
    //Create a wish menu
    @PostMapping("/wishCreator")
    public String renderWishCreator(){
        return "wishCreator";
    }
    //Creates a Wish-object and sends it to SQL
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
    //Gets the current User from session and diplays their Wishlist
    @PostMapping("/renderWishlist")
    public String renderWishlist(WebRequest request, Model model) throws SQLException {

        User user = (User) request.getAttribute("user",WebRequest.SCOPE_SESSION);

        model.addAttribute("wishlist",wishManager.viewWishlist(user));

        return "wishlistdisplay";
    }
    //For saving User in session
    private void setSessionInfo(WebRequest request, User user) {
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }
}