package com.prologiccreations.storemanagement.controller.config;

import com.prologiccreations.storemanagement.controller.super_classes.CrudController;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.config.Discount;
import com.prologiccreations.storemanagement.service.super_classes.config.DiscountService;
import com.prologiccreations.storemanagement.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("discount/")
public class DiscountController implements CrudController<Discount, Long> {
    private final DiscountService discountService;

    @Override
    public ResponseEntity<Response> storeData(Discount data) {
        Response response = discountService.storeData(data);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Page<Discount>>> getAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortColumns) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortDirection, sortColumns);
        Response<Page<Discount>> response = discountService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Discount>> getOne(Long id) {
        Response<Discount> response = discountService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response> delete(Long id) {
        Response response = discountService.delete(id);
        return ResponseEntity.ok(response);
    }
}
