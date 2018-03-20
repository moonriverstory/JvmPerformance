package heap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JavaHeapTest {

    public final static int OUT_OF_MEMORY = 200000000;

    private final Logger LOG = LoggerFactory.getLogger(JavaHeapTest.class);

    private String oom;

    private int length;

    StringBuffer tempOOM = new StringBuffer();

    public JavaHeapTest(int leng) {
        this.length = leng;

        int i = 0;
        while (i < leng) {
            i++;
            try {
                tempOOM.append("a");
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                LOG.error("OutOfMemoryError! ", e);
                break;
            }
        }
        this.oom = tempOOM.toString();
        try {
            Thread.sleep(1000*20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getOom() {
        return oom;
    }

    public int getLength() {
        return length;
    }

    public static void main(String[] args) {
        JavaHeapTest javaHeapTest = new JavaHeapTest(OUT_OF_MEMORY);
        System.out.println(javaHeapTest.getOom().length());
    }

}
