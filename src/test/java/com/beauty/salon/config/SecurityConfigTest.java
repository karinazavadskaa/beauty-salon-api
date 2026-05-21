package com.beauty.salon.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SecurityConfigTest {

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Test
    void testPasswordEncoder_ShouldExist() {
        assertThat(passwordEncoder).isNotNull();
    }

    @Test
    void testPasswordEncoder_ShouldEncodePassword() {
        if (passwordEncoder != null) {
            String rawPassword = "test123";
            String encoded = passwordEncoder.encode(rawPassword);
            assertThat(encoded).isNotNull();
            assertThat(encoded).isNotEqualTo(rawPassword);
        }
    }
}