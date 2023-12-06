package com.prologiccreations.storemanagement.controller.data;

import com.prologiccreations.storemanagement.controller.super_classes.CrudController;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.Order;
import com.prologiccreations.storemanagement.service.super_classes.data.OrderService;
import com.prologiccreations.storemanagement.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("order/")
public class OrderController implements CrudController<Order, Long> {
    private final OrderService orderService;

    @Override
    public ResponseEntity<Response> storeData(Order data) {
        Response response = orderService.storeData(data);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Page<Order>>> getAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortColumns) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortDirection, sortColumns);
        Response<Page<Order>> response = orderService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Order>> getOne(Long id) {
        Response<Order> response = orderService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response> delete(Long id) {
        Response response = orderService.delete(id);
        return ResponseEntity.ok(response);
    }
}
