package my.project.boot.ws.endpoint;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;

// 使用 @ServerEndpoint 注解表示此类是一个 WebSocket 端点
// 通过 value 指定 websocket 的路径
@Component
@ServerEndpoint(value = "/ws/test")
public class TestEndpoint {
    private Session session;

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received text：" + message);
        try {
            // 关闭连接：状态码为 NORMAL_CLOSURE（正常关闭）
            this.session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Bye"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnMessage
    public void onMessage(ByteBuffer bytes) {
        System.out.println("Received binary: " + bytes);
        try {
            // 关闭连接：状态码为 NORMAL_CLOSURE（正常关闭）
            this.session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Bye"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        System.out.println("opened");
        System.out.println("session: " + session);
        System.out.println("endpointConfig: " + endpointConfig);
        // 保存 session 到对象
        this.session = session;
    }

    @OnClose
    public void onClose(CloseReason closeReason){
        System.out.println("closed");
        System.out.println("closeReason: " + closeReason);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println("error: " + throwable);
        // 关闭连接：状态码为 UNEXPECTED_CONDITION（意料之外的异常）
        try {
            this.session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
