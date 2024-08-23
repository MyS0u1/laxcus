package my.project.boot.ws.handler;

import org.springframework.stereotype.Component;

@Component
public class FileHandler {
    /**
     * 登录存储管理服务器
     */
    public void login() {

    }

    /**
     * 向 Laxcus 存储文件
     *   1.从MinIO拉取
     *   2.存入 Laxcus
     *   3.返回存储结果
     */
    public void store() {

    }

    /**
     * 从 Laxcus 提取文件
     *   1.从 Laxcus 提取
     *   2.写入MinIO
     *   3.返回提取结果
     */
    public void extract() {

    }
}
