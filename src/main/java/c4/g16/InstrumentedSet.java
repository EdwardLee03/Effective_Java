package c4.g16;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 用<b><i>复合/转发的方法</i></b>来代替 {@link InstrumentedHashSet} 类。
 * 注意，这个实现分为两部分：<b><i>类本身和可重用的转发类（forwarding class）</i></b>，
 * 包含了所有的转发方法，没有其他方法。
 * <p/>
 * <b><i>{@link Set} 接口的存在使得 {@link InstrumentedSet} 类的设计成为可能，
 * 因为 Set 接口保存了 {@link java.util.HashSet HashSet} 类的功能特性。
 * 除了获得健壮性之外，这种设计也带来了格外的灵活性。
 * 从本质上讲，这个类把一个 Set 转变成了另一个 Set，同时增加了计数的功能。</i></b>
 * 与此不同的是，<b><i>这里的包装类（wrapper class）可以被用来包装任何 Set 实现，
 * 并且可以结合任何先前存在的构造器一起工作。</i></b>
 * <p/>
 * 因为<b><i>每一个 InstrumentedSet 实例都把另一个 Set 实例包装起来了</i></b>，
 * 所以 InstrumentedSet 类被称作<b><i>包装类（wrapper class）</i></b>。
 * 这也正是 <b><i>装饰（Decorator）模式</i></b>[Gamma95, p.175]，
 * 因为 <b><i>InstrumentedSet 类对一个集合进行了修饰，为它增加了计数特性</i></b>。
 * <p/>
 * <b><i>包装类几乎没有什么缺点。需要注意的一点是，包装类不适合用在回调框架（callback framework）中</i></b>；
 * 在回调框架中，对象把自身的引用传递给其他的对象，用于后续的调用（“回调”）。
 * <b><i>因为被包装起来的对象并不知道它外面的包装对象，所以它传递一个指向自身的引用（this），
 * 回调时避开了外面的包装对象。</i></b>这被称为 <b><i>SELF 问题</i></b>。
 * <p/>
 * <b><i>有些人担心转发方法调用所带来的性能影响，或者包装对象导致的内存占用。
 * 在实践中，这两者都不会造成很大的影响。</i></b>
 *
 * @author xingle
 * @since 2016年05月03日 22:51
 */
// Wrapper class - uses composition in place of inheritance
public class InstrumentedSet<E> extends ForwardingSet<E> {

    /**
     * The number of attempted element insertions
     */
    private int addCount = 0;

    public InstrumentedSet(Set<E> set) {
        super(set);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

}

/**
 * 可重用的转发类（Reusable forwarding class）
 *
 * @param <E> 集合元素类型
 */
class ForwardingSet<E> implements Set<E> {

    private final Set<E> set;

    public ForwardingSet(Set<E> set) {
        this.set = set;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean equals(Object o) {
        return set.equals(o);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public String toString() {
        return set.toString();
    }

}
