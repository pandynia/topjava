package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({Profiles.JDBC, Profiles.POSTGRES_DB})
public class JdbcMealServiceTest extends AbstractMealServiceTest {
    @Override
    @Test
    public void testValidation() throws Exception {}
}