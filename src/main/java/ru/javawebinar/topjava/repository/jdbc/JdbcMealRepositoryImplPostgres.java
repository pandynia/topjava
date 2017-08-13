package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;

/**
 * Created by Ruslan on 14.08.2017.
 */

@Repository
@Profile(Profiles.HSQLDB)
public class JdbcMealRepositoryImplPostgres extends JdbcMealRepositoryImpl {
    public JdbcMealRepositoryImplPostgres(DataSource dataSource) {
        super(dataSource);
    }
}
