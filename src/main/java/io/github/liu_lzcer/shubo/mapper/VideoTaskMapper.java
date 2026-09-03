package io.github.liu_lzcer.shubo.mapper;

import io.github.liu_lzcer.shubo.entity.VideoTask;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface VideoTaskMapper {
    VideoTask getTaskById(Long id);
    List<VideoTask> getTaskList(@Param("status") String status, @Param("offset") int offset, @Param("size") int size);
    long countTasks(@Param("status") String status);
    int createTask(VideoTask task);
}
