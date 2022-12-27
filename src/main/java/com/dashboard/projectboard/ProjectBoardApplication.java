package com.dashboard.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProjectBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectBoardApplication.class, args);
    }

}
