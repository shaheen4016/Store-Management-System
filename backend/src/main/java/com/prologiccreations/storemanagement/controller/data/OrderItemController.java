package com.prologiccreations.storemanagement.controller.data;

import com.prologiccreations.storemanagement.controller.super_classes.CrudController;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.OrderItem;
import com.prologiccreations.storemanagement.service.super_classes.data.OrderItemService;
import com.prologiccreations.storemanagement.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("orderItem/")
public class OrderItemController implements CrudController<OrderItem, Long> {
    private final OrderItemService orderItemService;

    @Override
    public ResponseEntity<Response> storeData(OrderItem data) {
        Response response = orderItemService.storeData(data);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Page<OrderItem>>> getAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortColumns) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortDirection, sortColumns);
        Response<Page<OrderItem>> response = orderItemService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<OrderItem>> getOne(Long id) {
        Response<OrderItem> response = orderItemService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response> delete(Long id) {
        Response response = orderItemService.delete(id);
        return ResponseEntity.ok(response);
    }
}
