package c4.g15;

/**
 * 表示一个复数，具有实部和虚部。
 * <p/>
 * <b><i>注意：这些算术运算是如何创建并返回新的 Complex 实例，而不是修改这个实例</i></b>。
 * 大多数重要的不可变类都使用了这种模式。它被称为<b><i>函数（functional）的做法</i></b>，
 * 因为这些方法返回了一个函数的结果，<b><i>这些函数对操作数进行运算但并不修改它</i></b>。
 * 与之相对应的是<b><i>过程的（procedural）或者命令式的（imperative）做法</i></b>，
 * 将一个过程作用在它们的操作数上，会<b><i>导致它的状态发生改变</i></b>。
 * <p/>
 * 函数方式的做法的许多优点：
 * <ul>
 * <li><b><i>不可变对象比较简单</i></b>（只有一种状态，即<b><i>被创建时的状态</i></b>）</li>
 * <li>不可变对象本质上是<b><i>线程安全的，它们不要求同步</i></b>（这无疑是<b><i>获得线程安全最容易的办法</i></b>）</li>
 * <li>不可变对象可以被自由地共享（永远也不需要进行保护性拷贝）</li>
 * <li>不可变对象为其他对象提供了大量的构件（building blocks）</li>
 * </ul>
 * 不可变类真正<b><i>唯一的缺点</i></b>是，<b><i>对于每个不同的值都需要一个单独的对象</i></b>。
 * <p/>
 * 不可变对象可以被自由地共享。
 * 不可变类应该充分利用这种优势，<b><i>鼓励客户端尽可能地重用现有的实例</i></b>。
 * 要做到这一点，一个很简单的办法就是，<b><i>对于频繁用到的值，为它们提供公有的静态 final 常量</i></b>。
 * <p/>
 * 上述这种方法可以被进一步扩展。<b><i>不可变的类可以提供一些静态工厂（见第1条）</i></b>，
 * 它们<b><i>把频繁被请求的实例缓存起来，从而当现有实例可以符合请求的时候，就不必创建新的实例</i></b>。
 * <b><i>所有基本类型的包装类和 BigInteger 都有这样的静态工厂</i></b>。
 * 使用这样的静态工厂也<b><i>使得客户端之间可以共享现有的实例，而不用创建新的实例，从而降低内存占用和垃圾回收的成本</i></b>。
 * <b><i>【好处】在设计新的类时，选择用静态工厂代替公有的构造器可以让你以后有添加缓存的灵活性，而不必影响客户端</i></b>。
 *
 * @author xingle
 * @since 2016年05月02日 21:47
 */
public final class Complex {

    /**
     * @see Complex2#valueOf(double, double)
     */
    public static Complex valueOf(double realPart, double imaginaryPart) {
        // See Integer.IntegerCache
        return null;
    }

    /**
     * 实部
     */
    private final double realPart;

    /**
     * 虚部
     */
    private final double imaginaryPart;

    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    // Accessors with no corresponding mutators
    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    // 4种基本的算术运算：加法、减法、乘法和除法
    public Complex add(Complex c) {
        return new Complex(realPart + c.realPart, imaginaryPart + c.imaginaryPart);
    }

    public Complex subtract(Complex c) {
        return new Complex(realPart - c.realPart, imaginaryPart - c.imaginaryPart);
    }

    public Complex multiply(Complex c) {
        return new Complex(
                realPart * c.realPart - imaginaryPart * c.imaginaryPart,
                realPart * c.imaginaryPart + imaginaryPart * c.realPart);
    }

    public Complex divide(Complex c) {
        double square = c.realPart * c.realPart + c.imaginaryPart * c.imaginaryPart;
        return new Complex(
                (realPart * c.realPart + imaginaryPart * c.imaginaryPart) / square,
                (imaginaryPart * c.realPart - realPart * c.imaginaryPart) / square);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Complex)) {
            return false;
        }

        Complex c = (Complex) o;

        // See page 43 to find out why we use compare instead of ==
        return Double.compare(c.realPart, realPart) == 0
                && Double.compare(c.imaginaryPart, imaginaryPart) == 0;

    }

    @Override
    public int hashCode() {
        int result = 17 + hashDouble(realPart);
        result = 31 * result + hashDouble(imaginaryPart);
        return result;
    }

    private static int hashDouble(double d) {
        long longBits = Double.doubleToLongBits(d);
        return (int) (longBits ^ (longBits >>> 32));
    }

    @Override
    public String toString() {
        // 符合数学表示
        return '(' + realPart + " + " + imaginaryPart + "i)";
    }

}
