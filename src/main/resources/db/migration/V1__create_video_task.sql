-- V1__create_video_task.sql
CREATE TABLE video_task (
    id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    title   VARCHAR(64) NOT NULL COMMENT '标题',
    status  VARCHAR(16) NOT NULL COMMENT '状态',
    error_message   VARCHAR(500) COMMENT '报错信息',
    create_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    update_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
                          ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '视频任务';