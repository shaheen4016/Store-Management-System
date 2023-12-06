package com.prologiccreations.storemanagement.service.config;

import com.prologiccreations.storemanagement.dao.config.CustomerRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.config.Customer;
import com.prologiccreations.storemanagement.service.super_classes.config.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Response storeData(Customer data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Customer>> getAll(Pageable pageable) {
        Page<Customer> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Customer> getById(Long id) {
        Customer customer = repository.findById(id).orElse(new Customer());
        return new Response<>(SUCCESS, null, customer);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Customer data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Customer data) {
//        boolean customernameExists;
//        if (data.hasId()) {
//            customernameExists = repository.existsByCustomername(data.getCustomername(), data.getId());
//        } else {
//            customernameExists = repository.existsByCustomername(data.getCustomername());
//        }
//        return customernameExists ? "Failed to register. Customer already exists" : null;
        return null;
    }

}
