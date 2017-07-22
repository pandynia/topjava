package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.Collection;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) throws NotFoundException {
        return repository.save(meal, userId);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {

        return repository.delete(id, userId);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return repository.get(id, userId);
    }

    @Override
    public Collection<MealWithExceed> getAll(int userId) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);

    }

    @Override
    public Collection<MealWithExceed> getBetween(LocalTime startTime, LocalTime endTime, int userId) {
        return MealsUtil.getFilteredWithExceeded(repository.getBetween(startTime, endTime, userId),
                startTime,
                endTime,
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}