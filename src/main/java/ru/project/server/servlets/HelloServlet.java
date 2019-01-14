package ru.project.server.servlets;

import com.google.gson.Gson;
import com.sun.istack.internal.NotNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends BaseServlet {

    public static final String HELLO_MESSAGE = "{\"message\": \"Привет, друг\"}";

    public HelloServlet(Gson gson) {
        super(gson);
    }

    @Override
    @NotNull
    String getPath() {
        return "/hello";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeStringResponse(resp, HELLO_MESSAGE);
    }
}
