package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal save(Meal meal) throws NotFoundException {
        return service.save(meal, AuthorizedUser.id());
    }

    public boolean delete(int id) throws NotFoundException {
        return service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) throws NotFoundException {
        return service.get(id, AuthorizedUser.id());
    }

    public Collection<MealWithExceed> getAll() {
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), MealsUtil.DEFAULT_CALORIES_PER_DAY);

    }

    public Collection<MealWithExceed> getBetween(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return MealsUtil.getFilteredWithExceeded(service.getBetween(startDate, endDate, AuthorizedUser.id()),
                startTime,
                endTime,
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}