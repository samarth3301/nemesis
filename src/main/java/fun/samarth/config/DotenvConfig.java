package fun.samarth.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DotenvConfig {

    static {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("PASSWORD", dotenv.get("PASSWORD"));
    }

    @Bean
    public Dotenv dotenv() {
        return Dotenv.load();
    }
}