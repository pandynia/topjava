package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );


        for (UserMealWithExceed umw: getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000)) {
            System.out.println(umw);
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return getFilteredJava8(mealList, startTime, endTime, caloriesPerDay);
        //return getFilteredJava7(mealList, startTime, endTime, caloriesPerDay);
    }


    //implementation by streams
    public static List<UserMealWithExceed> getFilteredJava8(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapDateCalories =
        mealList.stream()
                .collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate(), Collectors.summingInt(m -> m.getCalories())));

        //the final list preparation
        List<UserMealWithExceed> result =
        //filling the list
        mealList.stream()
                .filter(mDT -> TimeUtil.isBetween(mDT.getDateTime().toLocalTime(), startTime, endTime))
                .map(n -> new UserMealWithExceed(n.getDateTime(),
                        n.getDescription(),
                        n.getCalories(),
                        (mapDateCalories.get(n.getDateTime().toLocalDate()) > caloriesPerDay)))
                .collect(Collectors.toList());

        return result;
    }


    //implementation by loops
    public static List<UserMealWithExceed>  getFilteredJava7(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> mapDateCalories = new HashMap<>();
        for (UserMeal meal: mealList) {
            LocalDate key = meal.getDateTime().toLocalDate();
            Integer value = meal.getCalories();

            value = mapDateCalories.getOrDefault(key, 0) + value;
            mapDateCalories.put(key, value);
        }

        //final list
        List<UserMealWithExceed> result = new ArrayList<>();
        //filling the list
        for (UserMeal meal : mealList) {

            LocalDate mealDt = meal.getDateTime().toLocalDate();
            boolean exceed = (mapDateCalories.get(mealDt) > caloriesPerDay);

            LocalTime mealTime = meal.getDateTime().toLocalTime();

            if (TimeUtil.isBetween(mealTime, startTime, endTime))
                result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed));
        }

        return result;
    }

}
