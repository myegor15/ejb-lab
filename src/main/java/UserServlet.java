import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final String ACTION_ADD_USER = "addUser";
    private static final String ACTION_GET_ALL_USER = "getAllUser";
    private static final String ACTION_DELETE_USER = "deleteUser";
    private static final String ACTION_FIND_BY_USER_DATA = "findByUserData";

    @EJB
    private UserBeen userBeen;
    private Gson gson = new Gson();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String actionName = req.getParameter("actionName");
        switch (actionName) {
            case ACTION_ADD_USER: addUser(req, resp); break;
            case ACTION_GET_ALL_USER: getAllUser(req, resp); break;
            case ACTION_DELETE_USER: deleteUser(req, resp); break;
            case ACTION_FIND_BY_USER_DATA: findByUserData(req, resp); break;
            default: resp.sendError(404, "Unknown action name: " + actionName);
        }
    }

    private void findByUserData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = gson.fromJson(req.getReader(), User.class);
        List<User> userList = userBeen.findByUserData(user);
        String json = gson.toJson(userList);
        resp.getWriter().println(json);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = gson.fromJson(req.getReader(), User.class);
        userBeen.deleteUser(user);
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> allUser = userBeen.getAllUser();
        String json = gson.toJson(allUser);
        resp.getWriter().println(json);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = gson.fromJson(req.getReader(), User.class);
        userBeen.addUser(user);
    }
}
