package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    @Autowired
    private MealRestController controller;

    ConfigurableApplicationContext appCtx;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controller = appCtx.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        super.destroy();
        appCtx.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);

        controller.save(meal);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");


        if (action == null) {
            log.info("getAll");

            String dateFrom = request.getParameter("dateFrom");
            LocalDate startDate = (dateFrom == null || dateFrom.equals("")) ? LocalDate.MIN : DateTimeUtil.parseToLD(dateFrom);
            String dateTo = request.getParameter("dateTo");
            LocalDate endDate = (dateTo == null || dateTo.equals("")) ? LocalDate.MAX : DateTimeUtil.parseToLD(dateTo);
            String timeFrom = request.getParameter("timeFrom");
            LocalTime startTime = (timeFrom == null || timeFrom.equals("")) ? LocalTime.MIN : DateTimeUtil.parseToLT(timeFrom);
            String timeTo = request.getParameter("timeTo");
            LocalTime endTime = (timeTo == null || timeTo.equals("")) ? LocalTime.MAX : DateTimeUtil.parseToLT(timeTo);
            Collection<MealWithExceed> meals = controller.getBetween(startDate, endDate, startTime, endTime);
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
        else if (action.equals("delete")) {
            int id = getId(request);
            log.info("Delete {}", id);
            controller.delete(id);
            response.sendRedirect("meals");
        }
        else if (action.equals("create") || action.equals("update")) {
            final Meal meal = "create".equals(action) ?
                    new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                    controller.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/meal.jsp").forward(request, response);
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
