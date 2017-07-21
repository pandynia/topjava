package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(MealsUtil.MEALS.get(0), 0);
        save(MealsUtil.MEALS.get(1), 0);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        } else if (meals.get(meal.getId())==null){
            return null;
        }

        repository.put(meal.getId(), meals);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (!meals.isEmpty()) {
            return meals.remove(id, meals.get(id));
        } else {
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (meals != null) {
            return meals.get(id);
        }

        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (!meals.isEmpty()) {
            return meals.values().stream().
                    sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
        }
        else return Collections.EMPTY_LIST;
    }
}

