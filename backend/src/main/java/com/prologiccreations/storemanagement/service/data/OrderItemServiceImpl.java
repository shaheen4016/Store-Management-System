package com.prologiccreations.storemanagement.service.data;

import com.prologiccreations.storemanagement.dao.data.OrderItemRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.OrderItem;
import com.prologiccreations.storemanagement.service.super_classes.data.OrderItemService;
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
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository repository;

    @Override
    public Response storeData(OrderItem data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<OrderItem>> getAll(Pageable pageable) {
        Page<OrderItem> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<OrderItem> getById(Long id) {
        OrderItem orderItem = repository.findById(id).orElse(new OrderItem());
        return new Response<>(SUCCESS, null, orderItem);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(OrderItem data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(OrderItem data) {
//        boolean orderItemnameExists;
//        if (data.hasId()) {
//            orderItemnameExists = repository.existsByOrderItemname(data.getOrderItemname(), data.getId());
//        } else {
//            orderItemnameExists = repository.existsByOrderItemname(data.getOrderItemname());
//        }
//        return orderItemnameExists ? "Failed to register. OrderItem already exists" : null;
        return null;
    }

}
