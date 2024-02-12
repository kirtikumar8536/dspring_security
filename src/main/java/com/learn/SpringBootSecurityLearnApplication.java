package com.learn;

import com.learn.Entity.User;
import com.learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootSecurityLearnApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setEmail("kk@gmail.com");
        user1.setUsername("kirti");
        user1.setPassword(passwordEncoder.encode("kirti"));
        user1.setRole("ROLE_NORMAL");
        user1.setId(1L);

        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("admin@gmail.com");
        user2.setUsername("admin");
        user2.setPassword(passwordEncoder.encode("admin"));
        user2.setRole("ROLE_ADMIN");
        user2.setId(2L);

        userRepository.save(user2);

    }
}
