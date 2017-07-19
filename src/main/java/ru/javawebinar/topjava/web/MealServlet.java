package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDAO mealDAO;
    private final String INSERT_OR_EDIT = "/addeditform.jsp";
    private final String LIST_MEALS = "/meals.jsp";

    @Override
    public void init(ServletConfig servlet) throws ServletException {
        super.init(servlet);
        mealDAO = new MealDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        action = (action == null ? "" : action);

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            log.info(String.format("Delete with meal id %d", mealId));
            mealDAO.delete(mealId);
            request.setAttribute("mealsUtils", MealsUtil.getFilteredWithExceeded(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
            forward = LIST_MEALS;
        } else if (action.equalsIgnoreCase("update")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDAO.getById(mealId);
            log.info(String.format("Starting update with meal %s and id %d", meal, mealId));
            request.setAttribute("mealId",  mealId);
            request.setAttribute("meals", meal);
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("insert")){
            log.info("Starting insert a new meal");
            forward = INSERT_OR_EDIT;

        } else if (action.equalsIgnoreCase("list")){
            log.info("Starting viewing list of meals");
            request.setAttribute("mealsUtils", MealsUtil.getFilteredWithExceeded(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
            log.debug("redirect to meals");
            forward = LIST_MEALS;
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Integer id;
        id = Objects.equals(request.getParameter("id"), "") ? null : Integer.parseInt(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"), formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);

        if (id == null)
            mealDAO.insert(meal);
        else
            mealDAO.update(meal);

        log.debug("redirect to meals");
        request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        response.sendRedirect("meals?action=list");
    }
}
