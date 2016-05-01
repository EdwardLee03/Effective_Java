package c4.g13;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link Encapsulation}.
 *
 * @author xingle
 * @since 2016年05月01日 20:37
 */
public class EncapsulationTest {

    private Encapsulation encapsulation;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        if (encapsulation == null) {
            encapsulation = new Encapsulation();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (encapsulation != null) {
            // 若不手动进行关闭的话，主进程会一直运行着
            encapsulation.shutdown();
        }
    }

    @Test
    public void submitFuture() {
        String result = encapsulation.submitFuture();
        assertThat(result).isEqualTo("Callable.call() + Future.get(timeout, TimeUnit)");
    }

}
