package top.pullulate;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class PullulateApplication {

    public static void main(String[] args) {
        SpringApplication.run(PullulateApplication.class, args);
    }

}
