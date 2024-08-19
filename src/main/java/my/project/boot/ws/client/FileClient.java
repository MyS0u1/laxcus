package my.project.boot.ws.client;

import lombok.extern.slf4j.Slf4j;
import my.project.boot.common.util.SleepUtil;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

@Slf4j
public class FileClient extends WebSocketClient {
    public FileClient(URI serverUri) {
        super(serverUri);
    }

    public void startup() {
        log.info("WebSocket FileClient connect...");
        // 这里的连接为异步
        this.connect();
        /*
         * 平方探测休眠
         * 最大休眠时间为：∑(i=0,count) i * i * interval = interval * count(count + 1)(2*count + 1) / 6
               count    探测次数
               interval 探测间隔

               count interval(ms) amount(s)
               ----------------------------
               10    10           3.85
               10    100          38.5
               10    1000         385
         */
        for (int i = 0; i <= 10 && !this.getConnection().isOpen(); i++) {
            SleepUtil.sleep((long) 100 * i * i);
        }
        // 未连接无限重试
        if (!this.getConnection().isOpen()) {
            log.error("WebSocket FileClient startup timeout.");
            startup();
        }
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        // 去注册
    }

    @Override
    public void onMessage(String s) {

    }

    @Override
    public void onMessage(ByteBuffer bytes) {

    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
