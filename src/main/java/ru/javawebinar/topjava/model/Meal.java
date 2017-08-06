package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime =:dateTime, " +
                "m.description =:description, m.calories =:calories WHERE m.id =:id and m.user_id =:user_id"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM meals m " +
                "WHERE m.id =:id and m.user_id =:userId"),
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM meals m LEFT JOIN FETCH m.user " +
                "WHERE m.id =:id and m.user_id =:userId"),
        @NamedQuery(name = Meal.GET_ALL, query = "SELECT m FROM meals m " +
                "WHERE m.user_id =:userId ORDER BY m.dateTime desc"),
        @NamedQuery(name = Meal.GET_BETWEEN, query = "SELECT m FROM meals m " +
                "WHERE m.user_id =:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime desc")

})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "date_time"},
        name = "meals_user_datetime_un_idx")})
public class Meal extends BaseEntity {

    public static final String UPDATE = "Meal.update";
    public static final String DELETE = "Meal.delete";
    public static final String GET = "Meal.get";
    public static final String GET_ALL = "Meal.getAll";
    public static final String GET_BETWEEN = "Meal.getBetween";

    @Column(name="date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name="description", nullable = false)
    @NotNull
    private String description;

    @Column(name="calories", nullable = false)
    @NotNull
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
