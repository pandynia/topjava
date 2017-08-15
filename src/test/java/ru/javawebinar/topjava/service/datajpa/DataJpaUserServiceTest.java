package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by Ruslan on 14.08.2017.
 */
@ActiveProfiles({Profiles.DATAJPA})
public class DataJpaUserServiceTest extends UserServiceTest {
    @Test
    public void testGetWithMeal() throws Exception {
        User user = service.getWithMeals(USER_ID);
        List<Meal> meals = user.getMeals();
        MATCHER.assertEquals(USER, user);
        MealTestData.MATCHER.assertCollectionEquals(MEALS, meals);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() throws Exception {
        service.getWithMeals(1);
    }
}
