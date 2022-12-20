package com.anup.game.rockpaperscissors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
public class RockPaperScissorsGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(RockPaperScissorsGameApplication.class, args);
    }

}
