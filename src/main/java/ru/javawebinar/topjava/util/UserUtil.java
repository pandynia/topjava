package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(1, "Userok", "user@neskaz.hu", "password", Role.ROLE_USER),
            new User(2, "Odmin", "likeaboss@neskaz.hu", "P@s$w0r@", Role.ROLE_ADMIN)
    );

    public static User getUser() {
        return USERS.get(0);
    }

    public static User getAdmin() {
        return USERS.get(1);
    }
}