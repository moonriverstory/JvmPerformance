package memory.heap;

public class MemoryUsedTest {

    public final static int ONE_M_MEMORY = 1024*1024*1024/2;//一个char字符占用2个字节


    private String oom;

    private int length;

    StringBuffer holdMemory = new StringBuffer();

    public MemoryUsedTest(int leng) {
        this.length = leng;

        int i = 0;
        while (i < leng) {
            i++;
            try {
                holdMemory.append("a");
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                break;
            }
        }
        this.oom = holdMemory.toString();

    }

    public String getOom() {
        return oom;
    }

    public static void main(String[] args) {
        MemoryUsedTest javaHeapTest = new MemoryUsedTest(ONE_M_MEMORY*150);
        System.out.println(javaHeapTest.getOom().length());
        while (true){
            System.out.println("sleeping...");
            try {
                Thread.sleep(1000*200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
