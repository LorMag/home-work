package com.example.homework;

public record TaskSearchFilter(
        Long creatorId,
        Long assignedUserId,
        Status status,
        Priority priority,
        int pageSize,
        int pageNum
) {}
