package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Ruslan on 01.08.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    private Meal meal;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(USER_MEAL1.getId(), USER_ID);
        MATCHER.assertEquals(USER_MEAL1, meal);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(ADMIN_MEAL1_ID, USER_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(ADMIN_MEAL1_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN_MEAL2), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(ADMIN_MEAL1_ID, USER_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        Collection<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2015, 5, 30, 20, 0),
                LocalDateTime.of(2015, 6, 2, 22, 0), ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL2, ADMIN_MEAL1), meals);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> all = service.getAll(ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL2, ADMIN_MEAL1), all);
    }

    @Test
    public void update() throws Exception {
        Meal updated = USER_MEAL2;
        updated.setId(USER_MEAL2_ID);
        updated.setDescription("Завтрак");
        updated.setCalories(600);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(USER_MEAL2_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(ADMIN_MEAL1, USER_ID);
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.now(), "Полднег, ёпт", 6000);
        Meal created = service.save(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(created, ADMIN_MEAL2, ADMIN_MEAL1), service.getAll(ADMIN_ID));
    }

}