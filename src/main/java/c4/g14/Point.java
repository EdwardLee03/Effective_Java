package c4.g14;

/**
 * 在公有类中使用访问方法 而非公有域。
 * <p/>
 * Encapsulation of data by accessor methods and mutators.
 * 数据访问方法和设值方法的封装。
 * <p/>
 * JDK 的反例教材：{@link java.awt.Point}
 *
 * @author xingle
 * @since 2016年05月02日 20:30
 */
public class Point {

    // 私有域
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // 公有访问方法（getter）
    public double getX() {
        return x;
    }

    // 公有设值方法（setter）
    public Point setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Point setY(double y) {
        this.y = y;
        return this;
    }

}
