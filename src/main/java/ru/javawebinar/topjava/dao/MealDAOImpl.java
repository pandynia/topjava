package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ruslan on 16.07.2017.
 */
public class MealDAOImpl implements MealDAO {
    private static final Logger log = LoggerFactory.getLogger(MealDAOImpl.class);
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);

    {

        meals.put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void insert(Meal meal) {
        meal.setId(counter.getAndIncrement());
        log.debug(String.format("Insert meal %s with id %d to meals", meal, meal.getId()));
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(int mealsId) {
        log.debug(String.format("Delete a meal with id %d", counter.get()));
        meals.remove(mealsId);
    }

    @Override
    public void update(Meal meal) {
        if (!meal.isNullId(meal.getId()))
            log.debug(String.format("Update a meal with id %d", meal.getId()));
        else {
            log.error(String.format("A meal with id %d is not found", meal.getId()));
            return;
        }

        meals.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAll() {
        log.debug("Getting a list of meals");
        return new ArrayList<Meal>(meals.values());
    }

    @Override
    public Meal getById(int mealsId) {
        log.debug("Getting a meal by tne mealsId");
        return meals.get(mealsId);
    }
}
