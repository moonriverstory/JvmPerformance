package gc.reference;

public class GCRefObject {
    private String id;

    public GCRefObject(String id)
    {
        this.id = id;
    }

    public String toString()
    {
        return id;
    }

    //重写垃圾回收之前调用的方法，监控对象的gc
    public void finalize()
    {
        System.out.println("回收对象：" + id);
    }
}
