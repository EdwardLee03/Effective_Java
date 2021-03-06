/**
 * <h2>第17条：要么为继承而设计，并提供文档说明，要么就禁止继承</h2>
 * <p/>
 * 首先，该类的文档必须精确地描述覆盖每个方法所带来的影响。
 * 即，该类必须有文档说明它可覆盖（overridable）的方法的自用性（self-use）。
 * 见 {@link java.util.AbstractCollection#remove(java.lang.Object)}
 * <p/>
 * 关于程序文档有句格言：<b><i>好的 API 文档应该描述一个给定的方法做了什么工作，而不是描述它是如何做到的。</i></b>
 * 那么，上面这种做法是否违背了这句格言呢？是的，它确实违背了！
 * 这正是<b><i>继承破坏了封装性所带来的不幸后果。</i></b>
 *
 * @author xingle
 * @since 2016年05月04日 15:37
 */
package c4.g17;