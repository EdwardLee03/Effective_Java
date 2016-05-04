package c4.g17;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 子类覆盖超类构造器中调用的方法。
 * <p/>
 * 你可能会期待这个程序会打印出日期两次，但是它第一次打印出的是 null，
 * 因为 <b><i>overrideMe 方法被 Super 构造器调用的时候，构造器 Sub 还没有机会初始化 date 域。
 * 注意，这个程序观察到的 final 域处于两种不同的状态。
 * 还要注意，如果 overrideMe 已经调用了 date 中的任何方法，
 * 当 Super 构造器调用 overrideMe 的时候，调用就会抛出 NullPointerException 异常。</i></b>
 *
 * @author xingle
 * @since 2016年05月04日 16:05
 */
public final class Sub extends Super {

    private static final Logger logger = LoggerFactory.getLogger(Sub.class);

    private final Date date; // Blank final, set by constructor

    public Sub() {
        date = new Date();
    }

    // Overriding method invoked by superclass constructor
    @Override
    public void overrideMe() {
        logger.info("date: {}", date);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }

}
