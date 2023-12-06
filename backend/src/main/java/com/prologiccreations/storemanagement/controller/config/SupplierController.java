package com.prologiccreations.storemanagement.controller.config;

import com.prologiccreations.storemanagement.controller.super_classes.CrudController;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.config.Supplier;
import com.prologiccreations.storemanagement.service.super_classes.config.SupplierService;
import com.prologiccreations.storemanagement.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("supplier/")
public class SupplierController implements CrudController<Supplier, Long> {
    private final SupplierService supplierService;

    @Override
    public ResponseEntity<Response> storeData(Supplier data) {
        Response response = supplierService.storeData(data);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Page<Supplier>>> getAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortColumns) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortDirection, sortColumns);
        Response<Page<Supplier>> response = supplierService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Supplier>> getOne(Long id) {
        Response<Supplier> response = supplierService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response> delete(Long id) {
        Response response = supplierService.delete(id);
        return ResponseEntity.ok(response);
    }
}
