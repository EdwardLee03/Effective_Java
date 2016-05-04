package c4.g17;

/**
 * 继承约束：<b><i>构造器决不能调用可被覆盖的方法，无论是直接调用还是间接调用。</i></b>
 * <p/>
 * 如果违反了这条规则，很有可能导致程序失败。
 * <b><i>超类的构造器在子类的构造器之前运行</i></b>，所以，
 * <b><i>子类中覆盖版本的方法将会在子类的构造器运行之前就先被调用。
 * 如果该覆盖版本的方法依赖于子类构造器所执行的任何初始化工作，该方法将不会如预期般的执行。</i></b>
 *
 * @author xingle
 * @since 2016年05月04日 15:54
 */
public class Super {

    // Broken - constructor invokes an overridable method
    public Super() {
        overrideMe();
    }

    public void overrideMe() {
        //
    }

}
