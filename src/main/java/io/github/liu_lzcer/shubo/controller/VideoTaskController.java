package io.github.liu_lzcer.shubo.controller;

import io.github.liu_lzcer.shubo.dto.*;
import io.github.liu_lzcer.shubo.service.VideoTaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoTaskController {
    private final VideoTaskService videoTaskService;

    public VideoTaskController(VideoTaskService videoTaskService) {
        this.videoTaskService = videoTaskService;
    }

    @GetMapping("/api/v1/tasks/{id}")
    public ResponseEntity<ApiResponse<VideoTaskDTO>> getTaskById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(videoTaskService.getTask(id)));
    }

    @GetMapping("/api/v1/tasks")
    public ResponseEntity<ApiResponse<PageData<List<VideoTaskDTO>>>>
    getTaskList(@Valid VideoTaskQueryDTO videoTaskQueryDTO) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.ok(videoTaskService.getTaskQuery(videoTaskQueryDTO)));
    }

    @PostMapping("/api/v1/tasks")
    public ResponseEntity<ApiResponse<CreateVideoTaskDTO>>
    createTask(@Valid @RequestBody CreateVideoTaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.ok(videoTaskService.createVideoTask(request.title())));
    }
}
