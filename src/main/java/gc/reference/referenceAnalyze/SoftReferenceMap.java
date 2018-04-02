package gc.reference.referenceAnalyze;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * 继承map是为了在UIManager中 自由切换 map 和softregerencemap
 */
public class SoftReferenceMap<K, V> extends HashMap<K, V> {
    private static final long serialVersionUID = 1L;
    // 将 V 对象封装成软引用的对象，放置在SoftReferenceMap里面
    private HashMap<K, SoftValue<K, V>> temp;
    //用来存储键入了系统回收队列中的对象, 这里对应的是V
    private ReferenceQueue<V> queue;

    public SoftReferenceMap() {
        super();
        this.temp = new HashMap<K, SoftValue<K, V>>();
        queue = new ReferenceQueue<V>();
    }

    @Override
    public boolean containsKey(Object key) {
        // 清空正在被系统回收队列, 否则可能拿到的SoftReference中可能已经没有了 key对应的对象了
        clearMap();
        return temp.containsKey(key);
    }

    @Override
    public V get(Object key) {
        clearMap();
        SoftValue<K, V> softValue = temp.get(key);
        if (softValue != null) {
            return softValue.get();
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        SoftValue<K, V> softReference = new SoftValue<K, V>(key, value, queue);
        temp.put(key, softReference);
        return null;
    }

    // 从temp中清空 已经键入了回收队列的 对象(V) 对应的SoftReference
    @SuppressWarnings("unchecked")
    private void clearMap() {
        //从quene中拿到当前已经加入回收队列的最后一个V
        SoftValue<K, V> softReference = (SoftValue<K, V>) queue.poll();
        while (softReference != null) {
            temp.remove(softReference.key);//拿到这个V对应的key 把他从temp的map中删除
            softReference = (SoftValue<K, V>) queue.poll(); //循环把所有的在回收队列中的值从temp中删除
        }
    }

    //实现一个带键值对的SoftReference, 用来保存一个key供 temp 快速删除 对应的SoftReference
    @SuppressWarnings("hiding")
    private class SoftValue<K, V> extends SoftReference<V> {
        private K key;

        public SoftValue(K k, V v, ReferenceQueue<? super V> q) {
            super(v, q);//把SofrReference注册到Queue中, 系统会在适当时机把 V 加入到回收队列中.
            this.key = k;
        }
    }

}

