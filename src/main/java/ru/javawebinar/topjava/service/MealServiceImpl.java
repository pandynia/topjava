package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
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
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Collection<Meal> getBetween(LocalDateTime startDT, LocalDateTime endDT, int userId) {
        return repository.getBetween(startDT, endDT, userId);
    }
}