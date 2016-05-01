package c4.g13;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 公有的静态 final 域的安全漏洞。
 * <p/>
 * 要确保公有静态 final 域所引用的对象都是不可变的。
 *
 * @author xingle
 * @since 2016年05月01日 22:20
 */
public class StaticFieldPotentialSecurityHole {

    /**
     * <b><i>注意：长度非零的数组总是可变的！</i></b>
     * 所以，<b><i>类具有公有的静态 final 数组域，或者返回这种域的访问方法，这几乎总是错误的</i></b>。
     * 如果类具有这样的域或者访问方法，客户端将能够修改数组中的内容。
     * 这是安全漏洞的一个常见根源：
     */
    // Potential security hole! - 潜在的安全漏洞！
    public static final String[] VALUES = {};


    /*
     * 解决安全漏洞
     *
     * 要在这两种方法之间做出选择，得考虑客户端可能怎么处理这个结果。
     * 哪种返回类型会更加方便？哪种会得到更好的性能？
     */
    private static final String[] PRIVATE_VALUES = {};

    // 方法1：使公有数组变成私有的，并增加一个公有的不可变列表
    public static final List<String> SECURITY_LIST_VALUES =
            Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

    // 方法2：使数组变成私有的，并添加一个公有方法，它返回私有数组的一个备份
    public static final String[] values() {
        return PRIVATE_VALUES.clone();
    }

}
