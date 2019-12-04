package memory.heap;

import java.util.ArrayList;
import java.util.List;

public class MemoryUsedTest {

    public final static int ONE_M_MEMORY = 1024 * 1024;

    private final static String ONE_M_STRING;

    private static List<String> HOLD_MEM;

    static{
        StringBuilder oneMBuf = new StringBuilder(ONE_M_MEMORY);
        for (int i = 0; i < ONE_M_MEMORY; i++) {
            try {
                oneMBuf.append("a");
            } catch (OutOfMemoryError e) {
                System.out.println("init one mb OutOfMemoryError : i=" + i);
                e.printStackTrace();
                break;
            }
        }
        ONE_M_STRING = oneMBuf.toString();
    }

    public MemoryUsedTest(int size) {
        System.out.println("set list size : " + size);
        HOLD_MEM = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            try {
                HOLD_MEM.add(ONE_M_STRING);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public int getMemorySize() {
        return HOLD_MEM.size();
    }

    public static void main(String[] args) {
        MemoryUsedTest javaHeapTest = new MemoryUsedTest(150);
        System.out.println("Hold MB: " + javaHeapTest.getMemorySize());

        Runtime run = Runtime.getRuntime();
        long max = run.maxMemory();
        long total = run.totalMemory();
        long free = run.freeMemory();
        long usable = max - total + free;

        System.out.println("最大内存 = " + max);
        System.out.println("已分配内存 = " + total);
        System.out.println("已分配内存中的剩余空间 = " + free);
        System.out.println("最大可用内存 = " + usable);

        while (true) {
            System.out.println("sleeping...");
            try {
                Thread.sleep(1000 * 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
