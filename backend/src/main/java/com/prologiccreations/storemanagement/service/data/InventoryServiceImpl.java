package com.prologiccreations.storemanagement.service.data;

import com.prologiccreations.storemanagement.dao.data.InventoryRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.Inventory;
import com.prologiccreations.storemanagement.service.super_classes.data.InventoryService;
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
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    @Override
    public Response storeData(Inventory data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Inventory>> getAll(Pageable pageable) {
        Page<Inventory> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Inventory> getById(Long id) {
        Inventory inventory = repository.findById(id).orElse(new Inventory());
        return new Response<>(SUCCESS, null, inventory);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Inventory data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Inventory data) {
//        boolean inventorynameExists;
//        if (data.hasId()) {
//            inventorynameExists = repository.existsByInventoryname(data.getInventoryname(), data.getId());
//        } else {
//            inventorynameExists = repository.existsByInventoryname(data.getInventoryname());
//        }
//        return inventorynameExists ? "Failed to register. Inventory already exists" : null;
        return null;
    }

}
