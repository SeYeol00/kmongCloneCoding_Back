package com.sparta.kmongclonecoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KmongCloneCodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(KmongCloneCodingApplication.class, args);
    }

}
