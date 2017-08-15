package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Ruslan on 15.08.2017.
 */
@ActiveProfiles({Profiles.JDBC})
public class JdbcUserServiceTest extends UserServiceTest {
}
