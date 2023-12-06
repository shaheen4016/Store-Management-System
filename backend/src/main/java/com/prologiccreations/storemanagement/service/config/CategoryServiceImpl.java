package com.prologiccreations.storemanagement.service.config;

import com.prologiccreations.storemanagement.dao.config.CategoryRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.config.Category;
import com.prologiccreations.storemanagement.service.super_classes.config.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Response storeData(Category data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Category>> getAll(Pageable pageable) {
        Page<Category> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Category> getById(Long id) {
        Category category = repository.findById(id).orElse(new Category());
        return new Response<>(SUCCESS, null, category);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Category data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Category data) {
//        boolean categorynameExists;
//        if (data.hasId()) {
//            categorynameExists = repository.existsByCategoryname(data.getCategoryname(), data.getId());
//        } else {
//            categorynameExists = repository.existsByCategoryname(data.getCategoryname());
//        }
//        return categorynameExists ? "Failed to register. Category already exists" : null;
        return null;
    }

}
