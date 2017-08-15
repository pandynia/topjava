package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by Ruslan on 15.08.2017.
 */
@ActiveProfiles({Profiles.JDBC})
public class JdbcMealServiceTest extends MealServiceTest {
}
