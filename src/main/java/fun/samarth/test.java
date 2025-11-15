package fun.samarth;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class test {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        String DB_URL = dotenv.get("DB_URL");
        String USER = dotenv.get("DB_USER");
        String Password = dotenv.get("PASSWORD");

        System.out.println(DB_URL);
        System.out.println(USER);
        System.out.println(Password);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, Password);
             Statement statement = conn.createStatement()) {
            System.out.println("Connected to database.");

            // Fetch The statement from the database
            statement.executeQuery("""
                    CREATE TABLE users (
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(50) NOT NULL UNIQUE,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        created_at TIMESTAMP DEFAULT NOW()
                    );
                    """);
            ResultSet res = statement.executeQuery("""
                    SELECT table_schema, table_name
                    FROM information_schema.tables
                    WHERE table_type = 'BASE TABLE'
                      AND table_schema NOT IN ('pg_catalog', 'information_schema');
                    """);
            // Printing the result out.
            System.out.println(res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}