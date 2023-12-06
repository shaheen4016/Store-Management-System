package com.prologiccreations.storemanagement.dao.auth;

import com.prologiccreations.storemanagement.dao.super_classes.CrudDao;
import com.prologiccreations.storemanagement.model.auth.Permission;
import com.prologiccreations.storemanagement.model.auth.Role;
import com.prologiccreations.storemanagement.model.auth.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>, CrudDao<Permission, Long> {

    @Modifying
    @Query(value = "UPDATE Permission e " +
            "SET e.active = false " +
            "where e.id = :id")
    int softDeleteById(@Param("id") Long id);

}