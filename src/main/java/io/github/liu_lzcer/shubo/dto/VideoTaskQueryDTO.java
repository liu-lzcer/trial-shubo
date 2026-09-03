package io.github.liu_lzcer.shubo.dto;

import jakarta.validation.constraints.Min;

public record VideoTaskQueryDTO(@Min(value = 1, message = "page最小为1") Integer page
    , @Min(value = 1, message = "size最小为1") Integer size
    , String status) {
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 100;
    public VideoTaskQueryDTO {
        if(page == null) {
            page = DEFAULT_PAGE;
        }
        if(size == null) {
            size = DEFAULT_SIZE;
        }
        if(size > VideoTaskQueryDTO.MAX_SIZE) {
            size = MAX_SIZE;
        }
    }
    public int offset() {
        return (page - 1) * size;
    }
}
