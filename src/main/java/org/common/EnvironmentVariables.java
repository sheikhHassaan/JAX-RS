package org.common;

public class EnvironmentVariables {
    public static final String DB_USER = System.getenv("DB_USER");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    public static final String DB_URL = System.getenv("DB_URL");
    public static final String USERNAME = System.getenv("USER");
    public static final String PASSWORD = System.getenv("PASSWORD");
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_VALUE_PREFIX = "Basic ";
}
