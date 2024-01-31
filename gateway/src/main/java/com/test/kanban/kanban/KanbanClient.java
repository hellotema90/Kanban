package com.test.kanban.kanban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.test.kanban.client.BaseClient;


@Service
public class KanbanClient extends BaseClient {
    private static final String API_PREFIX = "/kanbans";

    @Autowired
    public KanbanClient(@Value("${kanban-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }
    /*
     public ResponseEntity<Object> createBooking(long userId, BookingPostRequestDto requestDto) {
        return post("", userId, requestDto);
    }

    public ResponseEntity<Object> approveBooking(long bookingId, long ownerId, boolean approved) {
        return patch("/" + bookingId + "?approved=" + approved, ownerId);
    }

    public ResponseEntity<Object> getBooking(long userId, long bookingId) {
        return get("/" + bookingId, userId);
    }
     */

}
