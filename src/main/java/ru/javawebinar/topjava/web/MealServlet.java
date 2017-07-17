package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDAO mealDAO;
    private final String INSERT_OR_EDIT = "/addeditform.jsp";
    private final String LIST_MEALS = "/meals.jsp";

    public void init() {
        mealDAO = new MealDAOImpl();
        mealDAO.insert(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealDAO.insert(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealDAO.insert(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealDAO.insert(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealDAO.insert(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));

    }

    private Map<Integer, MealWithExceed> getMealWithExceed(Map<Integer, Meal> mealMap) {
        List<Meal> values = new ArrayList<Meal>(mealMap.values());
        int i = 1;
        List<MealWithExceed> exceedList = MealsUtil.getFilteredWithExceeded(values, LocalTime.MIN, LocalTime.MAX, 2000);

        Map<Integer, MealWithExceed> mwe = new HashMap<>();
        for (MealWithExceed meal : exceedList) {
            mwe.put(i++, meal);
        }

        return mwe;
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
            request.setAttribute("mealsUtils",getMealWithExceed(mealDAO.getAll()));
        } else if (action.equalsIgnoreCase("update")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDAO.getById(mealId);
            request.setAttribute("mealId",  mealId);
            request.setAttribute("meals", meal);
        } else if (action.equalsIgnoreCase("list")){
            forward = LIST_MEALS;

            request.setAttribute("mealsUtils", getMealWithExceed(mealDAO.getAll()));
            log.debug("redirect to meals");
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int id = Integer.parseInt(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"), formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);

        mealDAO.update(id, meal);

        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        request.setAttribute("meals", getMealWithExceed(mealDAO.getAll()));
        view.forward(request, response);
    }
}
