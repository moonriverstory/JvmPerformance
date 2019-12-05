package memory.heap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemoryUsedTest {

    //java中一个char占用2个字节
    public final static int ONE_M_MEMORY = 1024 * 1024/2;

    private static List<String> HOLD_MEM;

    private static String ONE_MB;

    public MemoryUsedTest(int size, boolean unique) {
        System.out.println("set list size : " + size);
        if (!unique) {
            initOneMB();
        }
        HOLD_MEM = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            try {
                if (unique) {
                    HOLD_MEM.add(getUUIDOneMB());
                } else {
                    HOLD_MEM.add(ONE_MB);
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void initOneMB() {
        if (ONE_MB == null) {
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
            ONE_MB = oneMBuf.toString();
        }
    }

    private String getUUIDOneMB() {
        StringBuilder oneMBuf = new StringBuilder(ONE_M_MEMORY);
        int loop = ONE_M_MEMORY / 32;
        String uuid = get32UUIDStr();
        for (int i = 0; i < loop; i++) {
            try {
                oneMBuf.append(uuid);
            } catch (OutOfMemoryError e) {
                System.out.println("init one mb OutOfMemoryError : i=" + i);
                e.printStackTrace();
                break;
            }
        }
        return oneMBuf.toString();
    }

    /**
     * 生成32位uuid
     */
    public static String get32UUIDStr() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public int getMemorySize() {
        return HOLD_MEM.size();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        MemoryUsedTest javaHeapTest = new MemoryUsedTest(150, true);
        System.out.println("Holding  " + javaHeapTest.getMemorySize()+" MB string, and some things~ ");

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
