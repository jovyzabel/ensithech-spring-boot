package com.project.ensitech.testcontainers;

import com.project.ensitech.model.entity.User;
import com.project.ensitech.service.common.IUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Testcontainers
public class UserServiceImplTest {
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");

    @DynamicPropertySource
    static void config(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
    }

    @Autowired
    private IUser userRepository;

    @Test
    void testCreerEtListerUtilisateurs() {
        userRepository.creerUtilisateur("Alice", "alice@test.com");
        userRepository.creerUtilisateur("Bob", "bob@test.com");

        List<User> utilisateurs = userRepository.listerUtilisateurs();

        assertThat(utilisateurs).hasSize(2);
    }




}
