package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruslan on 16.07.2017.
 */
public class MealDAOImpl implements MealDAO {
    private Map<Integer, Meal> meals = new HashMap<>();

    @Override
    public void insert(Meal meal) {
        int mealsId = meals.size() + 1;
        meals.put(mealsId, meal);
    }

    @Override
    public void delete(int mealsId) {
        meals.remove(mealsId);
    }

    @Override
    public void update(int mealsId, Meal meal) {
        meals.put(mealsId, meal);
    }

    @Override
    public Map<Integer, Meal> getAll() {
        return  meals;
    }

    @Override
    public Meal getById(int mealsId) {
        return meals.get(mealsId);
    }
}
