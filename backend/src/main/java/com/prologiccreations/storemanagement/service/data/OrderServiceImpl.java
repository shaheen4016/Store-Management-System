package com.prologiccreations.storemanagement.service.data;

import com.prologiccreations.storemanagement.dao.data.OrderRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.Order;
import com.prologiccreations.storemanagement.service.super_classes.data.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Response storeData(Order data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Order>> getAll(Pageable pageable) {
        Page<Order> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Order> getById(Long id) {
        Order order = repository.findById(id).orElse(new Order());
        return new Response<>(SUCCESS, null, order);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Order data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Order data) {
//        boolean ordernameExists;
//        if (data.hasId()) {
//            ordernameExists = repository.existsByOrdername(data.getOrdername(), data.getId());
//        } else {
//            ordernameExists = repository.existsByOrdername(data.getOrdername());
//        }
//        return ordernameExists ? "Failed to register. Order already exists" : null;
        return null;
    }

}
