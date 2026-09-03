package com.example.homework.repository;

import com.example.homework.Status;
import com.example.homework.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("select count(t) from TaskEntity t where t.assignedUserId = :id and t.status = :status")
    Long findTasksByAssignedUserIdAndStatus(@Param("id") Long id, @Param("status") Status status);
}
