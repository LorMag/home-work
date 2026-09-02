package com.example.homework;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private Long creatorId;
    private Long assignedUserId;
    private Status status;
    private LocalDateTime createDateTime;
    private LocalDateTime deadlineDate;
    private Priority priority;

    public Task(Long id, Long creatorId, Long assignedUserId, Status status, LocalDateTime createDateTime, LocalDateTime deadlineDate, Priority priority) {
        this.id = id;
        this.creatorId = creatorId;
        this.assignedUserId = assignedUserId;
        this.status = status;
        this.createDateTime = createDateTime;
        this.deadlineDate = deadlineDate;
        this.priority = priority;
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
                '}';
    }
}
