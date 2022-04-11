/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.securitydemo12.servlets;

import ca.sait.securitydemo12.services.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marek PC
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/reset"})
public class ResetPasswordServlet extends HttpServlet {

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
    if (uuid != null){
    request.setAttribute("uuid",uuid);
    getServletContext().getRequestDispatcher("/WEB-INF/newpassword.jsp").forward(request, response);
    }else {
    getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
}
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
String url = request.getRequestURL().toString();
String message;
String path = getServletContext().getRealPath("/WEB-INF");
AccountService as = new AccountService();
String uuid = request.getParameter("uuid");

if (uuid != null) {
String password = request.getParameter("password");

if (email != null && password != null && as.changePassword(email, password, uuid)) {
message = "Password Successfully Updated! You can now Login with the new password!";


}else{
message = "Password Unsuccessfully Updated! PLease try again!";
request.setAttribute("uuid", uuid);

}

request.setAttribute("message", message);
getServletContext().getRequestDispatcher("/WEB-INF/newPassword.jsp").forward(request, response);


}else{
as.resetPassword(email, path, url);
message = "We will send you a link to rest you password from the given email. Please check your email for reset link.";
request.setAttribute("message", message);

getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
}
}
}