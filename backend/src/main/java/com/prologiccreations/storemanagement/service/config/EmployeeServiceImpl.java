package com.prologiccreations.storemanagement.service.config;

import com.prologiccreations.storemanagement.dao.config.EmployeeRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.config.Employee;
import com.prologiccreations.storemanagement.service.super_classes.config.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.prologiccreations.storemanagement.StoreManagementApplication.getCurrentUsername;
import static com.prologiccreations.storemanagement.constants.enums.OperationStatus.FAILURE;
import static com.prologiccreations.storemanagement.constants.enums.OperationStatus.SUCCESS;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Response storeData(Employee data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Employee>> getAll(Pageable pageable) {
        Page<Employee> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Employee> getById(Long id) {
        Employee employee = repository.findById(id).orElse(new Employee());
        return new Response<>(SUCCESS, null, employee);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Employee data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Employee data) {
//        boolean employeenameExists;
//        if (data.hasId()) {
//            employeenameExists = repository.existsByEmployeename(data.getEmployeename(), data.getId());
//        } else {
//            employeenameExists = repository.existsByEmployeename(data.getEmployeename());
//        }
//        return employeenameExists ? "Failed to register. Employee already exists" : null;
        return null;
    }

}
