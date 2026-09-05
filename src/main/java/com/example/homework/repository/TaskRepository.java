package com.example.homework.repository;

import com.example.homework.Priority;
import com.example.homework.Status;
import com.example.homework.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("select count(t) from TaskEntity t where t.assignedUserId = :id and t.status = :status")
    Long findTasksByAssignedUserIdAndStatus(@Param("id") Long id, @Param("status") Status status);

    @Query("""
            select t from TaskEntity t where
             (t.creatorId = :creatorId or :creatorId is null) and 
             (t.assignedUserId = :assignedUserId or :assignedUserId is null) and 
             (t.status = :status or :status is null) and 
             (t.priority = :priority or :priority is null)
             """)
    List<TaskEntity> searchAllByFilter(
            @Param("creatorId") Long creatorId,
            @Param("assignedUserId") Long assignedUserId,
            @Param("status") Status status,
            @Param("priority") Priority priority,
            Pageable pageable
    );
}
