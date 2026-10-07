package my.food;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("my.food.mapper")
public class Food1Application {

    public static void main(String[] args) {
        SpringApplication.run(Food1Application.class, args);
    }

}
