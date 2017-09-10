package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Ruslan on 10.09.2017.
 */
@RestController
@RequestMapping(MealAjaxController.REST_URL)
public class MealAjaxController extends AbstractMealController {
    static final String REST_URL = "/rest/profile/meals";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createWithLocation(@RequestParam("id") Integer id,
                                   @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                   @RequestParam("description") String description,
                                   @RequestParam("calories") Integer calories
                                   ) {
        Meal meal= new Meal(id, dateTime, description, calories);
        if (meal.isNew()) {
            super.create(meal);
        }
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endTime", required = false) LocalTime endTime)
    {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }


}
