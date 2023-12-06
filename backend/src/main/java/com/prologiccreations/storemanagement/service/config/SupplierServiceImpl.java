package com.prologiccreations.storemanagement.service.config;

import com.prologiccreations.storemanagement.dao.config.SupplierRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.config.Supplier;
import com.prologiccreations.storemanagement.service.super_classes.config.SupplierService;
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
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;

    @Override
    public Response storeData(Supplier data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Supplier>> getAll(Pageable pageable) {
        Page<Supplier> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Supplier> getById(Long id) {
        Supplier supplier = repository.findById(id).orElse(new Supplier());
        return new Response<>(SUCCESS, null, supplier);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Supplier data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Supplier data) {
//        boolean suppliernameExists;
//        if (data.hasId()) {
//            suppliernameExists = repository.existsBySuppliername(data.getSuppliername(), data.getId());
//        } else {
//            suppliernameExists = repository.existsBySuppliername(data.getSuppliername());
//        }
//        return suppliernameExists ? "Failed to register. Supplier already exists" : null;
        return null;
    }

}
