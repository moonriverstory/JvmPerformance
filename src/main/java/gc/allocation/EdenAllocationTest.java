package gc.allocation;

/**
 * 虚拟机参数为“-verbose:gc -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8”
 * -- -XX:+UseTLAB -XX:+PrintTLAB
 */
public class EdenAllocationTest {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args)
    {
        byte[] allocation1 = new byte[2 * _1MB];
        byte[] allocation2 = new byte[2 * _1MB];
        byte[] allocation3 = new byte[2 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB];
    }
    /**
     Heap
     PSYoungGen      total 9216K, used 7971K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     eden space 8192K, 97% used [0x00000000ff600000,0x00000000ffdc8c68,0x00000000ffe00000)
     from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
     PSPermGen       total 21504K, used 2941K [0x00000000f9a00000, 0x00000000faf00000, 0x00000000fec00000)
     object space 21504K, 13% used [0x00000000f9a00000,0x00000000f9cdf4c0,0x00000000faf00000)
     */

    //不同的垃圾收集器组合对于对象的分配是有影响的
}
