package cc.yuerblog.template;

/**
 * 用于遍历Vector的回调处理类
 * @author liangdong
 *
 * @param <T>
 */
public interface Walker<T> {
	/**
	 * 遍历回调函数
	 * @param value
	 */
	void onValue(T value);
}
