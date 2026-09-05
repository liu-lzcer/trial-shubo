package io.github.liu_lzcer.shubo.mapper;

import io.github.liu_lzcer.shubo.entity.VideoTask;
import io.github.liu_lzcer.shubo.enums.VideoTaskStatus;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface VideoTaskMapper {
    VideoTask getTaskById(Long id);
    List<VideoTask> getTaskList(@Param("status") VideoTaskStatus status, @Param("offset") int offset, @Param("size") int size);
    long countTasks(@Param("status") VideoTaskStatus status);
    int createTask(VideoTask task);
    int updateVideoTaskStatus(@Param("id") Long id, @Param("fromStatus") VideoTaskStatus fromStatus
        , @Param("toStatus") VideoTaskStatus toStatus, @Param("errorMessage") String errorMessage);
}
