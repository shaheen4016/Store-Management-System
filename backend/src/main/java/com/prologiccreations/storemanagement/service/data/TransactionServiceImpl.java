package com.prologiccreations.storemanagement.service.data;

import com.prologiccreations.storemanagement.dao.data.TransactionRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.Transaction;
import com.prologiccreations.storemanagement.service.super_classes.data.TransactionService;
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
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public Response storeData(Transaction data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Transaction>> getAll(Pageable pageable) {
        Page<Transaction> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Transaction> getById(Long id) {
        Transaction transaction = repository.findById(id).orElse(new Transaction());
        return new Response<>(SUCCESS, null, transaction);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Transaction data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Transaction data) {
//        boolean transactionnameExists;
//        if (data.hasId()) {
//            transactionnameExists = repository.existsByTransactionname(data.getTransactionname(), data.getId());
//        } else {
//            transactionnameExists = repository.existsByTransactionname(data.getTransactionname());
//        }
//        return transactionnameExists ? "Failed to register. Transaction already exists" : null;
        return null;
    }

}
