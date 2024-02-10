package com.example.timezone.service;

import com.example.timezone.dao.EmployeeInfoRepositoryDao;
import com.example.timezone.entity.EmployeeInfo;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Transactional
@Log4j2
public class EmployeeService {

    @Autowired
    private EmployeeInfoRepositoryDao employeeInfoRepository;

    public String addEmployeeDetails(EmployeeInfo employeeInfo){

        HashMap<String,String> resultMap = new HashMap<>();
        String employeeId = employeeInfo.getEmployeeId();
        if (isNotExistingEmployee(employeeId)) {
                try {
                    employeeInfoRepository.save(employeeInfo);
                    resultMap.put("STATUS","SUCCESS");
                    resultMap.put("RESULT","Employee with employee ID : " + employeeInfo.getEmployeeId() + " saved");
                }catch (Exception e){
                    log.error("Error while trying to persist data for employeeId : " + employeeInfo.getEmployeeId() + " ,stacktrace: " + e.getMessage());
                    resultMap.put("STATUS","FAILURE");
                    resultMap.put("RESULT","Error while trying to persist data for employeeId : " + employeeInfo.getEmployeeId());
                }
            if (resultMap.get("STATUS").equals("SUCCESS")) {
                return resultMap.get("RESULT");
            }
            else {
                return resultMap.get("RESULT");
            }
        }
        else {
            return "Employee with employee ID : " + employeeInfo.getEmployeeId() + " already exists";
        }
    }

    public Object getEmployeeInfoByEmpId(String employeeId){

        try {
            List<EmployeeInfo> employeeInfo = employeeInfoRepository.getEmployeeInfoByEmpId(employeeId);
            if (employeeInfo.size() != 0) {
                return employeeInfo;
            }
            else {
                return "Could not find employee details for employee ID : " + employeeId;
            }
        }catch (Exception e){
            log.error("Exception while fetching employee details for employee ID : " + employeeId);
            return "Some issue occured while fetching employee details for employee ID : " + employeeId;
        }
    }

    public boolean isNotExistingEmployee(String employeeId){
        boolean flag = false;
        int employeeCount = employeeInfoRepository.getEmployeeCount(employeeId);
        if (employeeCount == 0){
            flag = true;
        }
        return flag;
    }

    public List<EmployeeInfo> getAllEmployeeDetails() {

        try {
            return (List<EmployeeInfo>) employeeInfoRepository.findAll();
        }catch (Exception e){
            log.error("Exception while fetching all employee details : " + e.getMessage());
            return null;
        }
    }

    public String deleteEmployeeById(String id) {

        try {
            employeeInfoRepository.deleteEmployee(id);
            return "Employee details for employee ID : " + id + " deleted";
        }catch (Exception e){
            log.error("Exception while deleting data");
            return "Unable to delete employee data for employee ID : " + id;
        }
    }
}
