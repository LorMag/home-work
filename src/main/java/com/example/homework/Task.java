package com.example.homework;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
public class Task {
    @Null
    private Long id;
    @NotNull
    private Long creatorId;
    private Long assignedUserId;
    private Status status;
    private LocalDateTime createDateTime;
    @NotNull
    @Future
    private LocalDateTime deadlineDate;
    @NotNull
    private Priority priority;

    private LocalDateTime doneDateTime;

    public Task(
            Long id,
            Long creatorId,
            Long assignedUserId,
            Status status,
            LocalDateTime createDateTime,
            LocalDateTime deadlineDate,
            Priority priority,
            LocalDateTime doneDateTime
    ) {
        this.id = id;
        this.creatorId = creatorId;
        this.assignedUserId = assignedUserId;
        this.status = status;
        this.createDateTime = createDateTime;
        this.deadlineDate = deadlineDate;
        this.priority = priority;
        this.doneDateTime = doneDateTime;
    }

    public Long getId() {
        return id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDoneDateTime() {
        return doneDateTime;
    }

    public void setDoneDateTime(LocalDateTime doneDateTime) {
        this.doneDateTime = doneDateTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", assignedUserId=" + assignedUserId +
                ", status=" + status +
                ", createDateTime=" + createDateTime +
                ", deadlineDate=" + deadlineDate +
                ", priority=" + priority +
                ", doneDateTime=" + doneDateTime +
                '}';
    }
}
