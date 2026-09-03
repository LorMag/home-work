package com.example.homework;

import com.example.homework.repository.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toResponse(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getId(),
                taskEntity.getCreatorId(),
                taskEntity.getAssignedUserId(),
                taskEntity.getStatus(),
                taskEntity.getCreateDateTime(),
                taskEntity.getDeadlineDate(),
                taskEntity.getPriority()
        );
    }

}
