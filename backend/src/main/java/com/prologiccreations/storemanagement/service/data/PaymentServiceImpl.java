package com.prologiccreations.storemanagement.service.data;

import com.prologiccreations.storemanagement.dao.data.PaymentRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.Payment;
import com.prologiccreations.storemanagement.service.super_classes.data.PaymentService;
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
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    @Override
    public Response storeData(Payment data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Payment>> getAll(Pageable pageable) {
        Page<Payment> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Payment> getById(Long id) {
        Payment payment = repository.findById(id).orElse(new Payment());
        return new Response<>(SUCCESS, null, payment);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Payment data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Payment data) {
//        boolean paymentnameExists;
//        if (data.hasId()) {
//            paymentnameExists = repository.existsByPaymentname(data.getPaymentname(), data.getId());
//        } else {
//            paymentnameExists = repository.existsByPaymentname(data.getPaymentname());
//        }
//        return paymentnameExists ? "Failed to register. Payment already exists" : null;
        return null;
    }

}
