package thread;

public class ThreadDemo extends Thread {
    public static void main(String[] args) {
        ThreadDemo mt1 = new ThreadDemo("Thread a");
        ThreadDemo mt2 = new ThreadDemo("Thread b");
        mt1.setName("My-Thread-1 ");
        mt2.setName("My-Thread-2 ");
        mt1.start();
        mt2.start();
    }

    public ThreadDemo(String name) {
    }

    public void run() {
        cpu_hold();
    }

    private void cpu_hold(){
        while (true) {
            //妈耶，这个while true循环特别占cpu，两个线程即占用了60%的cpu Orz
        }
    }

}
