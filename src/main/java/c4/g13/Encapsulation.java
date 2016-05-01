package c4.g13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 封装/信息隐藏 示例代码。
 *
 * @author xingle
 * @since 2016年05月01日 19:42
 * @see PackagePrivate
 */
// 公有类都不应该包含公有域
// 包含公有可变域的类并不是线程安全的
public class Encapsulation {

    // 公有静态 final 域所引用的对象都是不可变的
    private static final Logger logger = LoggerFactory.getLogger(Encapsulation.class);


    // 实例域决不能是公有的
    private ExecutorService executorService;

    public Encapsulation() {
        executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * 提交一个异步计算任务。
     *
     * @return 异步计算结果
     */
    public String submitFuture() {
        Future<String> future = executorService.submit(
                new PrivateNestedClassCallable());

        try {
            return future.get(100L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.error("get PrivateNestedClassCallable Future failed", e);
        }
        return "";
    }

    /**
     * 关闭执行器服务。
     */
    public void shutdown() {
        executorService.shutdown();
    }

    /**
     * 私有嵌套类
     */
    private class PrivateNestedClassCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(10L);

            return "Callable.call() + Future.get(timeout, TimeUnit)";
        }

    }

}
