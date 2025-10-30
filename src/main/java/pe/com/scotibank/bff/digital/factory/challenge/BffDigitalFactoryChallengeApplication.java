package pe.com.scotibank.bff.digital.factory.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy
@PropertySource("classpath:database.properties")
@PropertySource("classpath:bff-digital-factory-challenge-v1.0.properties")
public class BffDigitalFactoryChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffDigitalFactoryChallengeApplication.class, args);
    }

}
