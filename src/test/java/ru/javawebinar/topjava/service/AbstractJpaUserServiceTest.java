package ru.javawebinar.topjava.service;

import org.junit.Test;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

/**
 * Created by Ruslan on 30.08.2017.
 */
public class AbstractJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", 9, true, Collections.emptySet())), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", 10001, true, Collections.emptySet())), ConstraintViolationException.class);
    }
}