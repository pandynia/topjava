package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;

/**
 * Created by Ruslan on 14.08.2017.
 */

@Profile(Profiles.HSQLDB)
public class JdbcMealRepositoryImplHsqldb extends JdbcMealRepositoryImpl {
    public JdbcMealRepositoryImplHsqldb(DataSource dataSource) {
        super(dataSource);
    }
}
