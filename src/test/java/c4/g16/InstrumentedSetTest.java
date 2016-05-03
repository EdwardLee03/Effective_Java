package c4.g16;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link InstrumentedSet}.
 *
 * @author xingle
 * @since 2016年05月03日 23:16
 */
public class InstrumentedSetTest {

    @Test
    public void getAddCount() {
        InstrumentedSet<String> instrumentedSet = new InstrumentedSet<>(
                new HashSet<String>());
        instrumentedSet.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        int addCount = instrumentedSet.getAddCount();

        assertThat(addCount).isEqualTo(3);

    }

}
