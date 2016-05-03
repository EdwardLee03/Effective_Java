package c4.g16;

import java.util.Collection;
import java.util.HashSet;

/**
 * 为了调优该程序的性能，需要查询 {@link HashSet}，看一看自从它被创建以来曾经添加了多少个元素。
 *
 * @author xingle
 * @since 2016年05月03日 21:03
 */
// Broken - Inappropriate use of inheritance!
public class InstrumentedHashSet<E> extends HashSet<E> {

    /**
     * The number of attempted element insertions
     */
    private int addCount = 0;

    public InstrumentedHashSet() {
        super();
    }

    public InstrumentedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    /**
     * 我们<b><i>只要去掉被覆盖的 {@link #addAll(Collection)} 方法，就可以“修正”这个子类。
     * 虽然这样得到的类可以正常工作，<b><i>但是，它的功能正确性则需要依赖于这样的事实：
     * {@link HashSet} 的 {@link HashSet#addAll(Collection) addAll} 方法是在它的 {@link HashSet#add(Object) add} 方法上实现的。
     * 这种“自用性（self-use）”是实现细节，不是承诺，不能保证在 Java 平台的所有实现中都保持不变，
     * 不能保证随着发行版本的不同而不发生变化。</i></b>
     * <p/>
     * <b><i>稍微好一点的做法是，覆盖 addAll 方法来遍历指定的集合，为每个元素调用一次 add 方法。</i></b>
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        // [Bug] 6 - actual
//        addCount += c.size();
//        return super.addAll(c); // 基于 super.add(e) 实现

        // 参考 AbstractCollection#addAll 实现
        // 这种方法很困难，也非常耗时，并且容易出错。
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    public int getAddCount() {
        return addCount;
    }

}
