package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Ruslan on 16.07.2017.
 */
public class MealDAOImpl implements MealDAO {
    private List<Meal> meals;

    public MealDAOImpl(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public void delete(int mealsId) {
        meals.remove(mealsId);
    }

    @Override
    public void update(Meal meal) {
        int index = meals.indexOf(meal);
        meals.set(index, meal);
    }

    @Override
    public List<Meal> getAll() {
        return  meals;
    }

    @Override
    public Meal getById(int mealsId) {
        return meals.get(mealsId);
    }
}
