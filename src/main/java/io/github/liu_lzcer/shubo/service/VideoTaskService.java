package io.github.liu_lzcer.shubo.service;

import io.github.liu_lzcer.shubo.dto.CreateVideoTaskDTO;
import io.github.liu_lzcer.shubo.dto.PageData;
import io.github.liu_lzcer.shubo.dto.VideoTaskDTO;
import io.github.liu_lzcer.shubo.dto.VideoTaskQueryDTO;
import io.github.liu_lzcer.shubo.entity.VideoTask;
import io.github.liu_lzcer.shubo.exception.TaskNotFoundException;
import io.github.liu_lzcer.shubo.mapper.VideoTaskMapper;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class VideoTaskService {
    private final VideoTaskMapper videoTaskMapper;

    public VideoTaskService(VideoTaskMapper videoTaskMapper) {
        this.videoTaskMapper = videoTaskMapper;
    }

    public CreateVideoTaskDTO createVideoTask(String title) {
        VideoTask task = new VideoTask();
        task.setTitle(title);
        task.setStatus("NEW");

        int row = videoTaskMapper.createTask(task);
        if(row == 0) {
            throw new IllegalStateException("视频任务创建失败，插入 0 行");
        }
        return new CreateVideoTaskDTO(task.getId(), title);
    }

    public VideoTaskDTO getTask(Long id) {
        VideoTask task = videoTaskMapper.getTaskById(id);
        if(task == null){
            throw new TaskNotFoundException(id + " id 不存在");
        }
        return VideoTaskDTO.from(task);
    }

    public long getTaskCount(String status) {
        return videoTaskMapper.countTasks(status);
    }

    public PageData<List<VideoTaskDTO>> getTaskQuery(@NonNull VideoTaskQueryDTO videoTaskQueryDTO) {
        List<VideoTask> list = videoTaskMapper.getTaskList(videoTaskQueryDTO.status()
            , videoTaskQueryDTO.offset(), videoTaskQueryDTO.size());
        return new PageData<>(list.stream().map(VideoTaskDTO::from).toList()
            , videoTaskQueryDTO.page(), videoTaskQueryDTO.size(), getTaskCount(videoTaskQueryDTO.status()));
    }
}
