package roll.me.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import roll.me.data.UsersDB;
import roll.me.model.Users;

@WebServlet(name = "UserHandler", urlPatterns = {"/login", "/register"})
public class UserHandler extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String URI = request.getRequestURI();
        String url = "/login.jsp";

        if(URI.endsWith("/login")){
            
            url = login(request, response);
        }
        if(URI.endsWith("/register")){
            
            url = register(request, response);
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String url = "/login.jsp";
        String message = "";
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        Users u = UsersDB.selectByName(name);

        if(u.getPassWord().equals(pass)){
            
            request.setAttribute("user", u);
            url = "/home.jsp";
        }
        else{
            
            message = "Enter valid Credentials";
            request.setAttribute("message", message);
        }
        
        return url;
    }

    private String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String url = "/register.jsp";
        String message = "";
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String confirmpass = request.getParameter("confirmpassword");
        String email = request.getParameter("email");
        String confirmemail = request.getParameter("confirmemail");

        if(!UsersDB.hasUser(email, name) && pass.equals(confirmpass) && email.equals(confirmemail)){

            Users u = new Users();
            u.setEmail(email);
            u.setPassWord(pass);
            u.setUserName(name);
            UsersDB.insertUser(u);
            url = "/home.jsp";
        }
        else {

            message = "A user with that username or email already exists";
            request.setAttribute("message", message);
        }

        return url;
    }
}
