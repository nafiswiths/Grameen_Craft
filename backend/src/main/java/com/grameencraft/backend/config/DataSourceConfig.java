package com.grameencraft.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        String dbUrl = System.getenv("DATABASE_URL");
        
        if (dbUrl == null || dbUrl.trim().isEmpty()) {
            System.out.println("DATABASE_URL environment variable is not set. Spring Boot will use in-memory H2 database fallback.");
            return DataSourceBuilder.create()
                    .driverClassName("org.h2.Driver")
                    .url("jdbc:h2:mem:grameendb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL")
                    .username("sa")
                    .password("")
                    .build();
        }

        try {
            // Clean up password brackets if present (e.g. postgresql://user:[pass]@host...)
            if (dbUrl.contains(":[") && dbUrl.contains("]@")) {
                dbUrl = dbUrl.replace(":[", ":").replace("]@", "@");
            }

            URI dbUri = new URI(dbUrl);
            String userInfo = dbUri.getUserInfo();
            String username = userInfo != null && userInfo.contains(":") ? userInfo.split(":")[0] : "";
            String password = userInfo != null && userInfo.contains(":") ? userInfo.split(":")[1] : "";
            
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            
            String dbUrlJdbc = "jdbc:postgresql://" + host + (port != -1 ? ":" + port : "") + path;

            if (!dbUrlJdbc.contains("?")) {
                dbUrlJdbc += "?sslmode=require";
            }

            System.out.println("Configuring Spring DataSource dynamically from DATABASE_URL.");

            return DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url(dbUrlJdbc)
                    .username(username)
                    .password(password)
                    .build();

        } catch (Exception e) {
            System.err.println("Failed to parse DATABASE_URL: " + e.getMessage() + ". Falling back to in-memory H2 database.");
            return DataSourceBuilder.create()
                    .driverClassName("org.h2.Driver")
                    .url("jdbc:h2:mem:grameendb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL")
                    .username("sa")
                    .password("")
                    .build();
        }
    }
}
