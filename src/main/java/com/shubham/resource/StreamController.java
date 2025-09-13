package com.shubham.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/stream")
public class StreamController {

    private static final Logger log = LoggerFactory.getLogger(StreamController.class);
    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/servlet", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public StreamingResponseBody streamServlet(HttpServletResponse response) {
        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);

        return outputStream -> {
            try {
                while (true) {
                    int value = random.nextInt(1000);
                    log.info("Value: {}", value);

                    Map<String, Object> map = Map.of("data", value, "message", "Data written");

                    // Write JSON string followed by newline for SSE format
                    String json = objectMapper.writeValueAsString(map) + "\n\n";
                    outputStream.write(json.getBytes());
                    outputStream.flush();

                    Thread.sleep(1000); // delay between events
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                log.info("Streaming interrupted");
            }
        };
    }

    @GetMapping(value = "/reactor", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, Object>> streamReactor() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(_ -> {
                    log.info(String.valueOf(Thread.currentThread()));
                    Map<String, Object> map = new HashMap<>();
                    map.put("data", random.nextInt(1000));
                    map.put("message", "Data written");
                    return map;
                })
                .log();
    }
}
