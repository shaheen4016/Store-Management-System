package com.prologiccreations.storemanagement.service.config;

import com.prologiccreations.storemanagement.dao.config.DiscountRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.config.Discount;
import com.prologiccreations.storemanagement.service.super_classes.config.DiscountService;
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
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository repository;

    @Override
    public Response storeData(Discount data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Discount>> getAll(Pageable pageable) {
        Page<Discount> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Discount> getById(Long id) {
        Discount discount = repository.findById(id).orElse(new Discount());
        return new Response<>(SUCCESS, null, discount);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Discount data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Discount data) {
//        boolean discountnameExists;
//        if (data.hasId()) {
//            discountnameExists = repository.existsByDiscountname(data.getDiscountname(), data.getId());
//        } else {
//            discountnameExists = repository.existsByDiscountname(data.getDiscountname());
//        }
//        return discountnameExists ? "Failed to register. Discount already exists" : null;
        return null;
    }

}
