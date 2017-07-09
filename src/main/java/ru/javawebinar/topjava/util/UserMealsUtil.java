package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

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
        //getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        for (UserMealWithExceed umw: getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000))
            System.out.println(umw);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        return getFilteredJava8(mealList, startTime, endTime, caloriesPerDay);
        //return getFilteredJava7(mealList, startTime, endTime, caloriesPerDay);
    }


    //имплементация стримами
    public static List<UserMealWithExceed> getFilteredJava8(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapDateCalories = new HashMap<>();

        mealList.stream()
                .collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate(), Collectors.summingInt(m -> m.getCalories())))
                .entrySet()
                .stream()
                .map(m -> mapDateCalories.put(m.getKey(), m.getValue()))
                .collect(Collectors.toList());

        //the final list preparation
        List<UserMealWithExceed> result = new ArrayList<>();
        //filling the list
        mealList.stream()
                .filter(mDT -> (!mDT.getDateTime().toLocalTime().isBefore(startTime) && !mDT.getDateTime().toLocalTime().isAfter(endTime)))
                .forEach(n -> {
                    boolean exceed = (mapDateCalories.get(n.getDateTime().toLocalDate()) > caloriesPerDay) ?
                            true :
                            false;

                    result.add(
                            new UserMealWithExceed(n.getDateTime(), n.getDescription(), n.getCalories(), exceed)
                    );
                });

        return result;
    }


    //имплементация циклами
    public static List<UserMealWithExceed>  getFilteredJava7(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        //insert into timeList
        //select *
        //from mealList
        //where dateTime.toLocalDate between startTime and endTime
        List<UserMeal> timeList = new ArrayList<>();
        Map<LocalDateTime, Integer> mapDateCalories = new HashMap<>();
        for (UserMeal meal: mealList) {
            LocalTime mealTime = meal.getDateTime().toLocalTime();

            if (!mealTime.isBefore(startTime) &&
                    !mealTime.isAfter(endTime))

                timeList.add(meal);

            //insert into dateCalories
            //select dateTime, calories
            //from mealList
            mapDateCalories.put(meal.getDateTime(), meal.getCalories());
        }


        Map<LocalDate, Integer> mapGroupedResult = new HashMap<>();
        //select dateTime, sum(Calories)
        //from dateCalories
        //group by dateTime
        for (Map.Entry<LocalDateTime, Integer> entry: mapDateCalories.entrySet()) {
            LocalDate key = entry.getKey().toLocalDate();
            Integer value = entry.getValue();

            value = (mapGroupedResult.get(key) != null) ?
                    mapGroupedResult.get(key) + value :
                    value;

            mapGroupedResult.put(key, value);
        }

        //готовим финальный список
        List<UserMealWithExceed> result = new ArrayList<>();
        //заполняем его вышеприведенным анализом
        for (UserMeal meal : timeList) {

            LocalDate mealDt = meal.getDateTime().toLocalDate();
            boolean exceed = (mapGroupedResult.get(mealDt) > caloriesPerDay) ?
                    true :
                    false;

            result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed));
        }

        return result;
    }

}
