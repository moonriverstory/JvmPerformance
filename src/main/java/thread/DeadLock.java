package thread;

/**
 * deadlock demo, 这个模拟棒极了
 * 就是一个线程获得两个对象上的两个锁，另一个线程同时获得这两个对象上的锁
 * 但是他们的获得和释放顺序是相反的
 * 并且这两个锁是嵌套关系
 * 已获得锁对象1的已经锁住线程1，等待锁2，才能继续执行，并释放锁1
 * 而获得锁对象2的已经锁住线程2，等待锁1，才能继续执行，并释放锁2
 * 这时，就形成了死锁！
 */
public class DeadLock {

    public static void main(String[] args) {
        Resource r1 = new Resource();
        Resource r0 = new Resource();

        Thread myTh1 = new LockThread1(r1, r0);
        Thread myTh0 = new LockThread0(r1, r0);

        myTh1.setName("DeadLock-1 ");
        myTh0.setName("DeadLock-0 ");

        myTh1.start();
        myTh0.start();
    }
}

class Resource {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

class LockThread1 extends Thread {
    private Resource r1, r2;

    public LockThread1(Resource r1, Resource r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void run() {
        int j = 0;
        while (true) {
            synchronized (r1) {
                System.out.println("The first thread got r1's lock " + j);
                synchronized (r2) {
                    System.out.println("The first thread got r2's lock " + j);
                }
            }
            j++;
        }
    }
}

class LockThread0 extends Thread {
    private Resource r1, r2;

    public LockThread0(Resource r1, Resource r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void run() {
        int j = 0;
        while (true) {
            synchronized (r2) {
                System.out.println("The second thread got r2's lock " + j);
                synchronized (r1) {
                    System.out.println("The second thread got r1's lock" + j);
                }
            }
            j++;
        }
    }
}