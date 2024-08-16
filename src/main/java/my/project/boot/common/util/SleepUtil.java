package my.project.boot.common.util;

public class SleepUtil {
    /**
     * 当前线程休眠指定毫秒
     *
     * @param millis 毫秒数
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
