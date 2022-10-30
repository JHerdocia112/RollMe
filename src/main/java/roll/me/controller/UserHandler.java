package roll.me.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import roll.me.data.UsersDB;
import roll.me.model.Users;
import roll.me.util.PasswordUtil;

@WebServlet(name = "UserHandler", urlPatterns = {"/login", "/register"})
public class UserHandler extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String URI = request.getRequestURI();
        String url = "/login.jsp";

        if(URI.endsWith("/login")){
            
            try {
                url = login(request, response);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                
                e.printStackTrace();
            }
        }
        if(URI.endsWith("/register")){
            
            try {
                url = register(request, response);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            
                e.printStackTrace();
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException{
        
        String url = "/login.jsp";
        String message = "";
        
        //login information is requested from the login page
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        Users u = new Users();

        //user object is qued from the databased based on username
        if(!UsersDB.hasUserByName(name)){
            
            //if false match user is prompted to re-enter credentials
            message = "Enter valid Credentials";
            request.setAttribute("message", message);
        }
        else{
            u = UsersDB.selectByName(name);
            //uses the verify function of password util to compare the unhashed and hashed pass of the user
            if(PasswordUtil.verify(pass, u.getPassWord())){
                
                //if true user logs in with assigned username
                request.setAttribute("user", u);
                url = "/home.jsp";
            }
            else{
            
                //if false match user is prompted to re-enter credentials
                message = "Enter valid Credentials";
                request.setAttribute("message", message);
            }
        }
        return url;
    }

    private String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException{
        
        String url = "/register.jsp";
        String message = "";
        
        //user information is requested from the registration page
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String confirmpass = request.getParameter("confirmpassword");
        String email = request.getParameter("email");
        String confirmemail = request.getParameter("confirmemail");

        //a user in the database must not have a matching email or username
        //a user must have the password and email confirmation inputs matching with the original
        if(!UsersDB.hasUserByName(name) && pass.equals(confirmpass) && email.equals(confirmemail)){
            
            //creates a new user based on information and is stored in database
            Users u = new Users();
            
            //generates a password hash
            pass = PasswordUtil.generate(pass);
            u.setEmail(email);
            u.setPassWord(pass);
            u.setUserName(name);
            UsersDB.insertUser(u);

            request.setAttribute("user", u);
            url = "/home.jsp";
        }
        else {
            //if user exists in the database or password/emails do not match
            message = "An Error has occured, please try again.";
            request.setAttribute("message", message);
        }

        return url;
    }
}
