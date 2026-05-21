package com.beauty.salon.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @Autowired(required = false)
    private CustomUserDetailsService userDetailsService;

    @Test
    void testUserDetailsService_ShouldExist() {
        assertThat(userDetailsService).isNotNull();
    }

    @Test
    void testLoadUserByUsername_WithAdminEmail_ShouldReturnUser() {
        if (userDetailsService != null) {
            var userDetails = userDetailsService.loadUserByUsername("admin@beauty.ru");
            assertThat(userDetails).isNotNull();
            assertThat(userDetails.getUsername()).isEqualTo("admin@beauty.ru");
        }
    }

    @Test
    void testLoadUserByUsername_WithCosmetologistEmail_ShouldReturnUser() {
        if (userDetailsService != null) {
            var userDetails = userDetailsService.loadUserByUsername("e.medvedeva@beauty.ru");
            assertThat(userDetails).isNotNull();
            assertThat(userDetails.getUsername()).isEqualTo("e.medvedeva@beauty.ru");
        }
    }

    @Test
    void testLoadUserByUsername_WithInvalidEmail_ShouldThrowException() {
        if (userDetailsService != null) {
            assertThrows(UsernameNotFoundException.class, () -> {
                userDetailsService.loadUserByUsername("nonexistent@test.ru");
            });
        }
    }
}