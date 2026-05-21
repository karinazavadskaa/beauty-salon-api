package com.beauty.salon.repository;

import com.beauty.salon.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    List<EmployeeRole> findByEmployeeId(Integer employeeId);
    List<EmployeeRole> findByRoleId(Integer roleId);
}