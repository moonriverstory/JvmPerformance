package gc.allocation.escape;

public class EscapeTest {
    public static Object obj;
    public void globalVariableEscape() { // 给全局变量赋值，发生逃逸
        obj = new Object();
    }
    public Object methodEscape() { // 方法返回值，发生逃逸
        return new Object();
    }
    public void instanceEscape() { // 实例引用发生逃逸 (--- 这个会吗？？？)
        //test(this); (test()任意一个其他类的方法)
    }
}
