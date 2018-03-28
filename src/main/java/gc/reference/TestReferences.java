package gc.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * 正常的对象引用都是强引用，其他几种较弱的，都需要用对应的reference对象包装一下=。=
 */
public class TestReferences {
    public static void main(String[] args) {
        int length = 10;

        //创建length个GCRefObject对象的强引用
        Set<GCRefObject> a = new HashSet<GCRefObject>();
        for (int i = 0; i < length; i++) {
            GCRefObject ref = new GCRefObject("Hard_" + i);
            System.out.println("创建强引用：" + ref);
            a.add(ref);
        }

        System.gc();

        //创建length个GCRefObject对象的软引用
        Set<SoftReference<GCRefObject>> sa = new HashSet<SoftReference<GCRefObject>>();
        for (int i = 0; i < length; i++) {
            SoftReference<GCRefObject> ref = new SoftReference<GCRefObject>(new GCRefObject("Soft_" + i));
            System.out.println("创建软引用：" + ref.get());
            sa.add(ref);
        }
        //只要内存足够，软引用是不会被回收的
        //内存不足，则会被回收
        System.gc();

        //创建length个GCRefObject对象的弱引用
        Set<WeakReference<GCRefObject>> wa = new HashSet<WeakReference<GCRefObject>>();
        for (int i = 0; i < length; i++) {
            WeakReference<GCRefObject> ref = new WeakReference<GCRefObject>(new GCRefObject("Weak_" + i));
            System.out.println("创建弱引用：" + ref.get());
            wa.add(ref);
        }
        //弱引用，只要gc就会被回收
        System.gc();

        //创建length个GCRefObject对象的虚引用
        ReferenceQueue<GCRefObject> rq = new ReferenceQueue<GCRefObject>();
        Set<PhantomReference<GCRefObject>> pa = new HashSet<PhantomReference<GCRefObject>>();
        for (int i = 0; i < length; i++) {
            PhantomReference<GCRefObject> ref = new PhantomReference<GCRefObject>(new GCRefObject("Phantom_" + i), rq);
            //PhantomReference.get()重写了get()方法，直接返回null
            //所以，虚引用包装的对象，什么也得不到，就是只能用来追踪gc调用(需重写finalize()方法)
            System.out.println("创建虚引用：" + ref.get() + " " + ref.toString());
            pa.add(ref);
        }
        System.gc();

    }
}
