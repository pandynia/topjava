package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Ruslan on 16.07.2017.
 */
public interface MealDAO {
    void insert(Meal meal);
    void delete(int mealsId);
    void update(Meal meal);
    List<Meal> getAll();
    Meal getById(int mealsId);
}
