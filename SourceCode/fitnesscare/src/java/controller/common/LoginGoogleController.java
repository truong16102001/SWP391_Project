package controller.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.impl.CartDAOImpl;
import dao.inter.IUserDAO;
import dao.impl.UserDAOImpl;
import dao.inter.ICartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Constants;
import model.User;
import model.UserGoogleDTO;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;

public class LoginGoogleController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDTO user_loginGG = getUserInfo(accessToken);

        System.out.println(user_loginGG.toString());
        IUserDAO ud = new UserDAOImpl();
        User user_db = ud.getUserByEmail(user_loginGG.getEmail());
        if (user_db == null) {
            ud.register(user_loginGG.getGiven_name() == null ? "" : user_loginGG.getGiven_name(), "", "1", user_loginGG.getEmail(), "", "");
            user_db = ud.getUserByEmail(user_loginGG.getEmail());
        }

        ud.login(user_db.getEmail(), user_db.getPassword());
        HttpSession session = request.getSession();
        String historyUrl = (String) session.getAttribute("historyUrl");
        ICartDAO cd = new CartDAOImpl();
        int totalItem = cd.getTotalItemInCart(user_db.getUser_id());
        session.setAttribute("totalItem", totalItem);
        session.setAttribute("us", user_db);
        response.sendRedirect(historyUrl);
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // response.sendRedirect("home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
