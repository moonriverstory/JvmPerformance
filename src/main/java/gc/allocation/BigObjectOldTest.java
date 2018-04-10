package gc.allocation;

/**
 * 虚拟机参数为“-XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728”
 *
 * 大对象直接进入老年代
 */
public class BigObjectOldTest {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args)
    {
        byte[] allocation = new byte[4 * _1MB];
    }
}
