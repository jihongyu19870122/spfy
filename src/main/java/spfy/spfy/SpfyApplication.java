package spfy.spfy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpfyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpfyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
    }
}

