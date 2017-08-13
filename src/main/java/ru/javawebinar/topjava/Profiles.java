package ru.javawebinar.topjava;

public class Profiles {
    public static final String
            POSTGRES="postgres",
            HSQLDB="hsqldb",
            JDBC = "jdbc",
            JPA = "jpa",
            DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;
    public static final String ACTIVE_DB = POSTGRES;
    public static final String ACTIVE_SPRING_PROFILE = DATAJPA;

    //  Get DB profile depending of DB driver in classpath
    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return POSTGRES;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return Profiles.HSQLDB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find DB driver");
            }
        }
    }
}
