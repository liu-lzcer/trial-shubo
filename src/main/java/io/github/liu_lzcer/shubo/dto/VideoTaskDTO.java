package io.github.liu_lzcer.shubo.dto;

import io.github.liu_lzcer.shubo.entity.VideoTask;
import io.github.liu_lzcer.shubo.enums.VideoTaskStatus;

import java.time.LocalDateTime;

public record VideoTaskDTO(Long id, String title, VideoTaskStatus status
    , LocalDateTime createdAt, LocalDateTime updatedAt, String errorMessage) {
    public static VideoTaskDTO from(VideoTask videoTask) {
        return new VideoTaskDTO(videoTask.getId(), videoTask.getTitle(), videoTask.getStatus()
            , videoTask.getCreatedAt(), videoTask.getUpdatedAt(), videoTask.getErrorMessage());
    }
}
