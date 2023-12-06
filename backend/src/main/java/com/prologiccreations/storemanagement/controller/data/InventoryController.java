package com.prologiccreations.storemanagement.controller.data;

import com.prologiccreations.storemanagement.controller.super_classes.CrudController;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.Inventory;
import com.prologiccreations.storemanagement.service.super_classes.data.InventoryService;
import com.prologiccreations.storemanagement.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("inventory/")
public class InventoryController implements CrudController<Inventory, Long> {
    private final InventoryService inventoryService;

    @Override
    public ResponseEntity<Response> storeData(Inventory data) {
        Response response = inventoryService.storeData(data);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Page<Inventory>>> getAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortColumns) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortDirection, sortColumns);
        Response<Page<Inventory>> response = inventoryService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Inventory>> getOne(Long id) {
        Response<Inventory> response = inventoryService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response> delete(Long id) {
        Response response = inventoryService.delete(id);
        return ResponseEntity.ok(response);
    }
}
