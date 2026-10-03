package com.phoenix.demo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.text.StringSubstitutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DELIBERATELY VULNERABLE. Test fixture for Phoenix Security remediation. Do not deploy.
 */
public final class App {

    private static final Logger LOG = LogManager.getLogger(App.class);

    private App() {
    }

    /** Weak hash: MD5 (CWE-328). */
    public static byte[] hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    /** SQL injection: user input concatenated into a query (CWE-89). */
    public static ResultSet findUser(Connection connection, String username) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM users WHERE name = '" + username + "'");
    }

    /** User input logged through log4j 2.14.1 (Log4Shell) and interpolated by commons-text 1.9. */
    public static String greet(String name) {
        LOG.info("Greeting " + name);
        return StringSubstitutor.createInterpolator().replace("Hello " + name);
    }

    public static void main(String[] args) {
        System.out.println(greet(args.length > 0 ? args[0] : "world"));
    }
}
