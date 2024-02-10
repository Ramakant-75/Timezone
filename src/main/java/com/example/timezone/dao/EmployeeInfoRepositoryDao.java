package com.example.timezone.dao;

import com.example.timezone.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface EmployeeInfoRepositoryDao extends CrudRepository<EmployeeInfo,Long> {

    @Query(value = "SELECT COUNT(*) FROM employee_info WHERE employee_id =:employeeId",nativeQuery = true)
    public int getEmployeeCount(String employeeId);

    @Query(value = "SELECT * FROM employee_info WHERE employee_id =:employeeId",nativeQuery = true)
    public List<EmployeeInfo> getEmployeeInfoByEmpId(String employeeId);

    @Modifying
    @Query(value = "DELETE FROM employee_info WHERE employee_id =:employeeId",nativeQuery = true)
    void deleteEmployee(String employeeId);
}
