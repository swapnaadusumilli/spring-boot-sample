package bootpoc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * User: victor
 * Date: 6/18/14
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
class Main {
    static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
