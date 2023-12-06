package com.prologiccreations.storemanagement.service.data;

import com.prologiccreations.storemanagement.dao.data.PurchaseRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.Purchase;
import com.prologiccreations.storemanagement.service.super_classes.data.PurchaseService;
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
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository repository;

    @Override
    public Response storeData(Purchase data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Purchase>> getAll(Pageable pageable) {
        Page<Purchase> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Purchase> getById(Long id) {
        Purchase inventory = repository.findById(id).orElse(new Purchase());
        return new Response<>(SUCCESS, null, inventory);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Purchase data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Purchase data) {
//        boolean inventorynameExists;
//        if (data.hasId()) {
//            inventorynameExists = repository.existsByPurchasename(data.getPurchasename(), data.getId());
//        } else {
//            inventorynameExists = repository.existsByPurchasename(data.getPurchasename());
//        }
//        return inventorynameExists ? "Failed to register. Purchase already exists" : null;
        return null;
    }

}
