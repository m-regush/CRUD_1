package Servlets;

import Model.User;
import Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "AddServlet",
        description = "Add user servlet",
        urlPatterns = {"/add"}
)

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/add.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String job = request.getParameter("job");
        Long salary = Long.parseLong(request.getParameter("salary"));
        User user = new User(name, job, salary);
        UserService service = new UserService();
        try {
            if (service.getClientByName(name) != null) {

            } else {
                service.addUser(user);
            }
            response.sendRedirect("users");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

}
