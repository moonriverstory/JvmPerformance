package memory.heap;

public class MemoryUsedTest {

    public final static int HOLD_MEMORY = 20000000;


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
        MemoryUsedTest javaHeapTest = new MemoryUsedTest(HOLD_MEMORY);
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
