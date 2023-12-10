package com.test.kanban.task;

import com.test.kanban.task.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.DefaultUriBuilderFactory;
import com.test.kanban.client.BaseClient;

import java.util.Map;


@Service
public class TaskClient extends BaseClient {
    private static final String API_PREFIX = "/tasks";

    @Autowired
    public TaskClient(@Value("${kanban-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> createTask(long userId, TaskDto taskDto) {
        return post("", userId, taskDto);
    }

    public ResponseEntity<Object> updateTask(Long userId, Long taskId, TaskDto taskDto) {
        return patch("/" + taskId, userId, taskDto);
    }

    public ResponseEntity<Object> getTaskById(long userId, long taskId) {
        return get("/" + taskId, userId);
    }

    public ResponseEntity<Object> getAllUserTasks(long userId, int from, int size) {
        Map<String, Object> parameters = Map.of("from", from, "size", size);
        return get("?from={from}&size={size}", userId, parameters);
    }

    public ResponseEntity<Object> deleteTask(long userId, long taskId) {
        return delete("/" + taskId, userId);
    }
}
