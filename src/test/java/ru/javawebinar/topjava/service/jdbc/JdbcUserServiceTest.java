package ru.javawebinar.topjava.service.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Before
    @Override
    public void setUp() {
        service.evictCache();
    }

    @Override
    @Test
    public void testValidation() throws Exception {}
}