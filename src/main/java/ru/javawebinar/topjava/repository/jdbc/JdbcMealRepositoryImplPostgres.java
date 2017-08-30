package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;
import java.time.LocalDateTime;

/**
 * Created by Ruslan on 14.08.2017.
 */

@Repository
@Profile(Profiles.POSTGRES)
public class JdbcMealRepositoryImplPostgres extends JdbcMealRepositoryImpl<LocalDateTime> {
    @Override
    protected LocalDateTime dateConvert(LocalDateTime dateTime) {
        return dateTime;
    }

    public JdbcMealRepositoryImplPostgres(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }
}
