package c4.g16;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link InstrumentedHashSet}.
 *
 * @author xingle
 * @since 2016年05月03日 21:09
 */
public class InstrumentedHashSetTest {

    /**
     * 这时候，我们期望 {@link InstrumentedHashSet#getAddCount()} 方法将会返回 3，但是它实际上返回的是 6。
     * <p/>
     * 哪里出错了呢？在 {@link HashSet} 的内部，{@link HashSet#addAll(Collection) addAll} 方法
     * 是基于它的 {@link HashSet#add(Object) add} 方法来实现的。
     */
    @Test
    public void getAddCount() {
        InstrumentedHashSet<String> instrumentedHashSet = new InstrumentedHashSet<>();
        instrumentedHashSet.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        int addCount = instrumentedHashSet.getAddCount();

//        assertThat(addCount).isEqualTo(6); // 3 - expected
        assertThat(addCount).isEqualTo(3);
    }

}
