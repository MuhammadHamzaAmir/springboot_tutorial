package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student minahil = new Student("Minahil", LocalDate.of(2000, Month.MAY,20),"mk@gmail.com");
            Student fatima = new Student("Fatima", LocalDate.of(2000, Month.FEBRUARY,20),"fg@gmail.com");

            repository.saveAll(List.of(minahil,fatima));

        };
    }
}
