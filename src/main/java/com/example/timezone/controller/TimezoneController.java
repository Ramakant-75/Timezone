package com.example.timezone.controller;

import com.example.timezone.dao.EmployeeInfoRepositoryDao;
import com.example.timezone.entity.EmployeeInfo;
import com.example.timezone.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/timesheet")
public class TimezoneController {

    @Autowired
    private EmployeeInfoRepositoryDao employeeInfoRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employeeinfo")
    @CrossOrigin
    public Object getEmployeeInfo(@RequestParam(required = false) String id ){
        log.info("<--- calling /employeeinfo api --->");
        if (null != id || !StringUtils.isEmpty(id)){
            try {
                return employeeService.getEmployeeInfoByEmpId(id);
            }catch (Exception e){
                log.error("Exception while fetching employee details for employee ID : " + id);
                return "Error while fetching employee details for employee ID : " + id;
            }
        }
        else {
            log.info("returning all employee details");
            try {
                return employeeService.getAllEmployeeDetails();
            }catch (Exception e){
                log.error("Error while fetching all employee details : " + e.getMessage());
                return "Error while fetching all employee details";
            }
        }
    }

    @PostMapping("/addemployee")
    @CrossOrigin
    public String addEmployeeDetails(@RequestBody EmployeeInfo employeeInfo){
        log.info("<--- calling /addemployee api --->");
        try {
        String result = employeeService.addEmployeeDetails(employeeInfo);
            return result;
        }catch (Exception e){
            log.error("Exception in /addemployee api");
            return "Error while saving employee details for employee ID : " + employeeInfo.getEmployeeId();
        }
    }

    @DeleteMapping("/delemployee/{id}")
    @CrossOrigin
    public String deleteEmployeeDetails(@PathVariable String id){
        log.info("<--- calling delemployee API --->");
        try {
            return employeeService.deleteEmployeeById(id);
        }catch (Exception e){
            log.error("Error while deleting employee details with employee ID : " + id);
            return "Unable to delete employee details with employee ID : " +id;
        }
    }
}
