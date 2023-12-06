package com.prologiccreations.storemanagement.service.config;

import com.prologiccreations.storemanagement.dao.config.ProductRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.config.Product;
import com.prologiccreations.storemanagement.service.super_classes.config.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Response storeData(Product data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Product>> getAll(Pageable pageable) {
        Page<Product> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Product> getById(Long id) {
        Product product = repository.findById(id).orElse(new Product());
        return new Response<>(SUCCESS, null, product);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Product data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Product data) {
//        boolean productnameExists;
//        if (data.hasId()) {
//            productnameExists = repository.existsByProductname(data.getProductname(), data.getId());
//        } else {
//            productnameExists = repository.existsByProductname(data.getProductname());
//        }
//        return productnameExists ? "Failed to register. Product already exists" : null;
        return null;
    }

}
