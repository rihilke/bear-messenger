package servlets;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersListServlet extends HttpServlet {

    DBService dbService = new DBService();

    List<UsersDataSet> usersList;

    public void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        int usersCount = 0;

        {
            try {
                usersList = dbService.getAllDbUsers();
                usersCount = usersList.size();

            } catch (DBException e) {
                e.printStackTrace();
            }
        }


        if (usersCount == 0) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Нет юзеров");

        } else {/*
            for (UsersDataSet user; usersList) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Registered: " + user.getName());
                */
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html;charset=utf-8");

            for (UsersDataSet usersDataSet : usersList) {
                response.getWriter().println("Registered: " + usersDataSet.getName());
                response.getWriter().println("<br>");
            }

            }

        }


    }

