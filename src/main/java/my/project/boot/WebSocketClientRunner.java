package my.project.boot;

import lombok.extern.slf4j.Slf4j;
import my.project.boot.ws.client.FileClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class WebSocketClientRunner implements ApplicationRunner {
    @Resource
    private FileClient fileClient;

    @Override
    public void run(ApplicationArguments args) {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() ->  fileClient.startup())
        ).join();
    }
}
