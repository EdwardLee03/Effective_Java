package c4.g15;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * 不可变类真正<b><i>唯一的缺点</i></b>是，<b><i>对于每个不同的值都需要一个单独的对象</i></b>。
 * <p/>
 * 【问题】如果你执行一个多步骤的操作，并且<b><i>每个步骤都会产生一个新的对象，
 * 除了最后的结果之外其他的对象最终都会被丢弃，此时性能问题就会暴露出来</i></b>。
 * <p/>
 * 处理这种问题有两种方法：
 * 第一种办法，先猜测一下会经常用到哪些多步骤的操作，然后将它们作为基本类型提供。
 * 如果<b><i>能够精确的预测出客户端将要在不可变的类上执行哪些复杂的多阶段操作</i></b>，
 * 这种<b><i>包级私有的可变配套类的方法</i></b>就可以工作得很好。
 * 如果<b><i>无法预测，最好的办法是提供一个公有的可变配套类</i></b>。
 * 在 Java 平台类库中，这种方法的主要例子是 {@link String} 类，
 * 它的可变配套类是 {@link StringBuilder}（和基本上已经废弃的 StringBuffer）。
 *
 * @author xingle
 * @since 2016年05月03日 11:37
 */
public class BitOperationExample {

    /**
     * 一百万
     */
    private static final int ONE_MILLION = 100 * 10000;

    /**
     * 假设你有一个上百万位的 {@link BigInteger}，想要改变它的低位。
     * <p/>
     * {@link BigInteger} 是不可变的
     */
    public void flipBit() {
        BigInteger bigInteger = BigInteger.valueOf(Long.MAX_VALUE);
        bigInteger.flipBit(0); // 基于 BigInteger.valueOf(int[]) 实现
    }

    /**
     * {@link BitSet} 是可变的。
     * BitSet 类提供了一个方法，允许在固定时间（constant time）内改变此“百万位”实例中单个位的状态。
     */
    public void flip() {
        BitSet bitSet = new BitSet(ONE_MILLION);
        bitSet.flip(0);
    }

    /**
     * 不可变的类必须为 final 的。
     * 必须在假设它可能是可变的前提下对它进行保护性拷贝（见第39条）。
     */
    public static BigInteger safeInstance(BigInteger value) {
        if (value.getClass() != BigInteger.class) {
            return new BigInteger(value.toByteArray());
        }
        return value;
    }

}
