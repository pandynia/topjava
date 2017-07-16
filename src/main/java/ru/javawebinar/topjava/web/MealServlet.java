package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealDAO;
import ru.javawebinar.topjava.service.MealDAOImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private List<Meal> meals = new ArrayList<>();
    private List<MealWithExceed> mealsUtils = new ArrayList<>();
    private MealDAO mealDAO;
    private final String INSERT_OR_EDIT = "/mealsNewEdit.jsp";
    private final String LIST_MEALS = "/meals.jsp";

    public MealServlet() {
        meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        mealDAO = new MealDAOImpl(meals);

    }

    public void init() {
        mealsUtils = new MealsUtil().getFilteredWithExceeded(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        init();

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealDAO.delete(mealId);
            forward = LIST_MEALS;
            request.setAttribute("users", mealDAO.getAll());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDAO.getById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_MEALS;
            request.setAttribute("users", mealDAO.getAll());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

        log.debug("redirect to meals");


        request.setAttribute("mealsUtils", mealsUtils);

        request.getRequestDispatcher(forward).forward(request, response);
        //response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);

        mealDAO.update(meal);

        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        request.setAttribute("meals", mealDAO.getAll());
        view.forward(request, response);
    }
}
