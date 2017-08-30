package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;

/**
 * Created by Ruslan on 15.08.2017.
 */
@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends UserServiceTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testGetWithUserNotFound() throws Exception {
        service.getWithMeals(MEAL1_ID);
    }
}
