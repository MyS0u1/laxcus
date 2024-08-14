package my.project.boot.ws.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

public class TestWebSocketClient extends WebSocketClient {
    public TestWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("opened");
        System.out.println("serverHandshake: " + handshake.getHttpStatus());
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received text: " + message);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        System.out.println("Received binary: " + bytes);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed");
        System.out.println("Code: " + code);
        System.out.println("Reason: " + reason);
        System.out.println("Remote: " + remote);
    }

    @Override
    public void onError(Exception e) {
        System.out.println("error: " + e);
    }

    public static void main(String[] args) throws Exception {
        URI uri = new URI("ws://localhost:8080/ws/test"); // 替换为实际的WebSocket服务器地址
        TestWebSocketClient client = new TestWebSocketClient(uri);
        client.connect();

        // 等待连接建立
        for (int i = 0; i <= 10 && !client.getConnection().isOpen(); i++) {
            if (i == 9) {
                throw new RuntimeException("Failed to connect to the WebSocket server.");
            } else {
                // amount 5.5 (second)
                // System.out.println("sleep: " + i * 100L);
                Thread.sleep(i * 100L);
            }
        }
        // 发送文本消息
        client.send("你是猪！");

        // 发送二进制消息
        ByteBuffer byteBuffer = ByteBuffer.wrap("你是猪！".getBytes());
        client.send(byteBuffer);

        // 已经在服务端的 onMessage 里关闭了，这里会导致意外情况
        // client.close();
    }
}
