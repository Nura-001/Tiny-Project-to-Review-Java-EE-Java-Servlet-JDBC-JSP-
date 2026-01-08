package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb",
                "root",
                "YOUR_MYSQL_PASSWORD"
            );

            // Insert Query
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);

            pst.executeUpdate();

            con.close();

            response.sendRedirect("success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
