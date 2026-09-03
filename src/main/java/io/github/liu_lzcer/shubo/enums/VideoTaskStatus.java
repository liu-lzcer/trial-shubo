package io.github.liu_lzcer.shubo.enums;

import java.util.Map;
import java.util.Set;

public enum VideoTaskStatus {
    NEW, RUNNING, DONE, FAILED;
/* 新建、进行中、已完成、失败 */

    private static final Map<VideoTaskStatus, Set<VideoTaskStatus>> ALLOWED = Map.of(
        NEW, Set.of(RUNNING),
        RUNNING, Set.of(DONE, FAILED),
        DONE, Set.of(),
        FAILED, Set.of()
    );

    public boolean canGoTo(VideoTaskStatus target) {
        return ALLOWED.get(this).contains(target);
    }

    public boolean isTerminal() {
        return ALLOWED.get(this).isEmpty();
    }
}
