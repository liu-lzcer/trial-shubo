## 介绍

本项目为数字人后端的 java 重构

## 启动方式

.\mvnw spring-boot:run

## 环境变量

SPRING_PROFILES_ACTIVE=
DB_HOST=
DB_PORT=
DB_NAME=
DB_USER=
DB_PASSWORD=

## 接口清单

### 通用约定

- 前缀 `/api/v1`，资源用复数名词，无尾斜杠。
- 请求体和响应体都是 JSON，`Content-Type: application/json`。
- 成功与失败都返回统一响应体，code 为 0 表示成功，非 0 见错误码表；traceId 当前恒为 null，接入日志链路后填充。

```json
{"code": 0, "message": "ok", "data": null, "traceId": null}
```

### 接口

| 接口     | 方法与路径                                  | 成功状态码 | 请求                                                         | 响应 data                                                  |
| -------- | ------------------------------------------- | ---------- | ------------------------------------------------------------ | ---------------------------------------------------------- |
| 创建任务 | POST /api/v1/tasks                          | 201        | JSON 体 `{"title": "开学视频"}`，title 必填、去空白后非空、最长 64 字符 | `{"id": 7, "title": "开学视频"}`                           |
| 查单个   | GET /api/v1/tasks/{id}                      | 200        | 路径参数 id                                                  | 任务对象                                                   |
| 查列表   | GET /api/v1/tasks?status=NEW&page=1&size=10 | 200        | 三个查询参数都可省                                           | `{"data": [任务对象], "page": 1, "size": 10, "total": 15}` |

任务对象：

```json
{
  "id": 7,
  "title": "开学视频",
  "status": "NEW",
  "createdAt": "2026-09-02T10:15:30.123",
  "updatedAt": "2026-09-02T10:15:30.123",
  "errorMessage": null
}
```

### 分页规则

- page 从 1 起，缺省 1；size 缺省 10，上限 100，超过 100 按 100 处理，响应里的 size 是实际生效值。
- page 或 size 小于 1 返回 400。
- status 可省，省略时不过滤，按字符串原样匹配。
- 列表按 updated_at、created_at、id 倒序。
- 无匹配数据或页码越界返回 200，`data.data` 为空数组，total 照常返回。

### 错误码表

| HTTP | code  | message                              | 场景                                      | data               |
| ---- | ----- | ------------------------------------ | ----------------------------------------- | ------------------ |
| 400  | 40001 | 参数校验失败                         | 空 title、title 超长、page 或 size 小于 1 | 字段错误列表，见下 |
| 404  | 40401 | `{id} id 不存在`，如 `999 id 不存在` | 查了一个不存在的 id                       | null               |
| 500  | 50000 | 系统繁忙, 请稍后再试                 | 未被业务异常覆盖的一切                    | null               |

400 的 data 是字段错误列表，每项三个键：

```json
[{"fieldName": "title", "rejectedValue": "", "message": "title 不能为空"}]
```
