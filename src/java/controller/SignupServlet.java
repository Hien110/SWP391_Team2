/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.regex.Pattern;
import model.User;
import repository.UserRepository;
import service.RandomCodeGenerator;
import static service.RandomCodeGenerator.generateRandomCode;
import service.sendEmail;
import static service.sendEmail.sendEmail;

/**
 *
 * @author ADMIN
 */
public class SignupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignupServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        response.sendRedirect("signup.jsp");
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("re_password");
        UserRepository cdb = new UserRepository();
        User c = cdb.getAccountByUsername(username);
        User e = cdb.getAccountByEmail(email);
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_-]*$");
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"); // Ít nhất 1 chữ và 1 số, độ dài từ 6 trở lên
        if (c != null) {
            String ms = "Username has already existed";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./signup.jsp").forward(request, response);
        } else if (e != null) {
            String ms = "Email has already existed";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./signup.jsp").forward(request, response);
        } else if (!usernamePattern.matcher(username).find()) {
            String ms = "Username must only contain letters, numbers and - and _";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./signup.jsp").forward(request, response);
        } else if (!passwordPattern.matcher(password).matches()) {
            String ms = "Password must be at least 6 characters long and contain at least one letter and one number";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./signup.jsp").forward(request, response);
        } else if (!password.equals(repassword)) {
            String ms = "Re-password not same password";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./signup.jsp").forward(request, response);
        } else {
            String randomCode = generateRandomCode();
            sendEmail(username, email, randomCode);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("randomCode", randomCode);
            response.sendRedirect("./verify");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
