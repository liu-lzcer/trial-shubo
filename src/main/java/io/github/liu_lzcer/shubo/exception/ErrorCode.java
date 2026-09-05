package io.github.liu_lzcer.shubo.exception;

public enum ErrorCode {
    INVALID_PARAM(40001, "参数校验失败"),
    VIDEO_TASK_NOT_FOUND(40401, "任务不存在"),
    INTERNAL_ERROR(50000, "系统繁忙, 请稍后再试"),
    VIDEO_TASK_STATUS_CONFLICT(40901, "当前状态不允许该操作");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String message() {
        return message;
    }

    public int code() {
        return code;
    }
}
