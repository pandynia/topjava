package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;

/**
 * Created by Ruslan on 14.08.2017.
 */

@Profile(Profiles.HSQLDB)
public class JdbcMealRepositoryImplHsqldb extends JdbcMealRepositoryImpl {
    public JdbcMealRepositoryImplHsqldb(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }
}
