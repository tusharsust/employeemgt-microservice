package com.softcode.notificationserver.service;

import com.softcode.notificationserver.model.Employee;
import com.softcode.notificationserver.model.EmployeeDuty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employeeserver")
public interface EmployeeServiceClient {

    @GetMapping(value = "/v1/employees/{id}")
    Employee getEmployeeById(@PathVariable(name="id") Integer id);

    @GetMapping(value = "/v1/employee-duties/{id}")
    EmployeeDuty getEmployeeDuty(@PathVariable(name="id") Integer id);

}
