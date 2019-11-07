package Servlets;

import Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "DeleteServlet",
        description = "Delete user servlet",
        urlPatterns = {"/delete"}
)

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/delete.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        UserService service = new UserService();
        try {
            if (service.getClientByName(name) != null) {
                service.deleteUser(name);
                response.sendRedirect("users");
            } else if (service.getClientByName(name) == null && name.equals("")) {
                response.sendRedirect("users");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
