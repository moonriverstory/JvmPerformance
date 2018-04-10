package gc.allocation.escape;

import java.io.IOException;

/**
 * jvm参数：-Xmx1G -XX:+DoEscapeAnalysis -XX:-EliminateLocks
 *
 * jdk1.8默认开启 锁消除
 */
public class StackDisableLock {
    public static void alloc(){
        byte[] b=new byte[65];
        synchronized (b) { //同步代码块
            b[0]=1;
        }
    }
    public static void main(String[] args) throws IOException {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
    /**
     锁消除打开
     1528

     锁消除关闭
     3013
     */
}
