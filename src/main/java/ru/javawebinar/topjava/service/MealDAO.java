package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Ruslan on 16.07.2017.
 */
public interface MealDAO {
    public void delete(int mealsId  );
    public void update(Meal meal );
    public List<Meal> getAll();
    public Meal getById(int mealsId );
}
