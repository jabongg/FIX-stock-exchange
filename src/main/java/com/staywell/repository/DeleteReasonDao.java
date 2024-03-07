package com.staywell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staywell.entity.DeleteReason;

public interface DeleteReasonDao extends JpaRepository<DeleteReason, Long>{

}
