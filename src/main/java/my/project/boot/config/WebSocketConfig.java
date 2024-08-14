package my.project.boot.config;

import my.project.boot.ws.client.FileClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.net.URI;

@Configuration
public class WebSocketConfig {
    @Value("${ws.file.uri}")
    private String fileUri;

    @Bean
    public ServerEndpointExporter serverEndpointExporter (){
        // 手动注册 WebSocket 端点
        // exporter.setAnnotatedEndpointClasses(TestEndpoint.class);

        return new ServerEndpointExporter();
    }

    @Bean
    public FileClient fileClient() {
        return new FileClient(URI.create(fileUri));
    }
}
