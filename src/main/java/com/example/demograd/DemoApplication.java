package com.example.demograd;

import com.example.demograd.Dao.UserDao;
import com.example.demograd.Entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(DemoApplication.class);
    }

    @Bean
    public CommandLineRunner loadData(UserDao userDao) {
        return (args) -> {
//            userDao.save(new User("John", "Kenya"));

//            User user = userDao.findById(1);
        };
    }

}
