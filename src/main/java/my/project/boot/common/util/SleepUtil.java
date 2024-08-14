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


    /**
     * 等差探测休眠
     * 最大休眠时间为：max_sleep = ∑(i=0,count) i * interval = count * (interval + interval*count) / 2
     *     count interval(ms) amount(s)
     *     10    10           0.55
     *     10    100          5.5
     *     10    1000         55
     *
     * @param count     循环次数
     * @param interval  间隔毫秒
     * @param condition 条件
     */
    public static void arithmeticSleep(int count, int interval, boolean condition) {
        for (int i = 0; i <= count && condition; i++) {
            sleep((long) i * interval);
        }
    }

    /**
     * 平方探测休眠
     * 最大休眠时间为：max_sleep = ∑(i=0,count) i * i * interval = interval * count(count + 1)(2*count + 1) / 6
     *     count interval(ms) amount(s)
     *     10    10           3.85
     *     10    100          38.5
     *     10    1000         385
     *
     * @param count     循环次数
     * @param interval  间隔毫秒
     * @param condition 条件
     */
    public static void squareSleep(int count, int interval, boolean condition) {
        for (int i = 0; i <= count && condition; i++) {
            sleep((long) i * i * interval);
        }
    }
}
