package io.github.liu_lzcer.shubo.dto;

public record PageData<T>(T data, int page, int size, long total) {
}
