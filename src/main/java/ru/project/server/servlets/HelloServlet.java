package ru.project.server.servlets;

import com.google.gson.Gson;
import ru.project.server.storage.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloServlet extends BaseServlet {

    public HelloServlet(Gson gson) {
        super(gson);
    }

    public static final String TABLE = "customers";

    @Override
    String getPath() {
        return "/hello";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBHelper helper = DBHelper.getInstance();
        try (Connection connection = helper.getConnection()) {
            helper.runQuery(connection, "SELECT * from " + TABLE + ';', rs -> {
                rs.next();
                String restName = rs.getString("name");
                try {
                    String responseText = "{\"message\": \"" + restName + "\"}";
                    writeStringResponse(resp, responseText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
