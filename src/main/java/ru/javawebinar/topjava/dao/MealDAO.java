package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Map;

/**
 * Created by Ruslan on 16.07.2017.
 */
public interface MealDAO {
    public void insert(Meal meal);
    public void delete(int mealsId);
    public void update(int mealId, Meal meal);
    public Map<Integer, Meal> getAll();
    public Meal getById(int mealsId);
}
