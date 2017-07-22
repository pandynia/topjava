package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.Collection;

public interface MealService {
    Meal save(Meal meal, int userId) throws NotFoundException;

    boolean delete(int id, int userId) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    Collection<MealWithExceed> getAll(int userId);

    Collection<MealWithExceed> getBetween(LocalTime startTime, LocalTime endTime, int userId);
}