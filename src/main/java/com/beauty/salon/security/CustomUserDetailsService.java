package com.beauty.salon.security;

import com.beauty.salon.entity.Employee;
import com.beauty.salon.entity.User;
import com.beauty.salon.repository.EmployeeRepository;
import com.beauty.salon.repository.EmployeeRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeRoleRepository employeeRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        String role = employeeRoleRepository.findByEmployeeId(employee.getId())
                .stream()
                .findFirst()
                .map(er -> er.getRole().getName())
                .orElse("USER");

        // ВАЖНО: используем {noop} для plain text паролей
        String rawPassword = email.split("@")[0] + "123";
        String passwordWithNoop = "{noop}" + rawPassword;

        System.out.println("Email: " + email);
        System.out.println("Raw password: " + rawPassword);
        System.out.println("Password with noop: " + passwordWithNoop);
        System.out.println("Role: " + role);

        return new User(employee.getId(), employee.getEmail(), passwordWithNoop, role);
    }
}