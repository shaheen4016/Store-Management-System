package com.prologiccreations.storemanagement.service.data;

import com.prologiccreations.storemanagement.dao.data.ReviewRepository;
import com.prologiccreations.storemanagement.dto.Response;
import com.prologiccreations.storemanagement.model.data.Review;
import com.prologiccreations.storemanagement.service.super_classes.data.ReviewService;
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
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    @Override
    public Response storeData(Review data) {
        String validationMsg = validate(data);
        if (validationMsg == null) {
            repository.save(data);
            return new Response(SUCCESS, "Successfully stored data", null);
        } else {
            return new Response(FAILURE, validationMsg, null);
        }
    }

    @Override
    public Response<Page<Review>> getAll(Pageable pageable) {
        Page<Review> page = repository.findByActive(true, pageable);
        return new Response<>(SUCCESS, null, page);
    }

    @Override
    public Response<Review> getById(Long id) {
        Review review = repository.findById(id).orElse(new Review());
        return new Response<>(SUCCESS, null, review);
    }

    @Override
    public Response delete(Long id) {
        repository.softDeleteById(id, getCurrentUsername(), LocalDateTime.now());
        return new Response(SUCCESS, "Deleted successfully", null);
    }

    @Override
    public String validate(Review data) {
        return checkDuplicate(data);
    }

    @Override
    public String checkDuplicate(Review data) {
//        boolean reviewnameExists;
//        if (data.hasId()) {
//            reviewnameExists = repository.existsByReviewname(data.getReviewname(), data.getId());
//        } else {
//            reviewnameExists = repository.existsByReviewname(data.getReviewname());
//        }
//        return reviewnameExists ? "Failed to register. Review already exists" : null;
        return null;
    }

}
