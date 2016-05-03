package c4.g15;

/**
 * <b><i>让不可变的类变成 final 的另一种办法</i></b>就是，
 * <b><i>让类的所有构造器都变成私有的或者包级私有的，
 * 并添加公有的静态工厂（static factory）来代替共有的构造器（见第1条）</i></b>。
 *
 * @author xingle
 * @since 2016年05月03日 15:50
 */
// Immutable class with static factories instead of constructors
public class Complex2 {

    /**
     * 实部
     */
    private final double realPart;

    /**
     * 虚部
     */
    private final double imaginaryPart;

    private Complex2(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    /**
     * 参考 {@link Integer#valueOf(int)} 实现
     */
    public static Complex2 valueOf(double realPart, double imaginaryPart) {
        return new Complex2(realPart, imaginaryPart);
    }

}
