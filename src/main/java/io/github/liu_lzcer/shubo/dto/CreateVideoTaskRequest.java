package io.github.liu_lzcer.shubo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateVideoTaskRequest(@NotBlank(message = "title 不能为空")
                                     @Size(max = 64, message = "title 过长 应当少于64字符") String title) {
}
