package cc.yuerblog.template;

import java.util.ArrayList;

/**
 * 叫做Vector的数组容器
 * @author liangdong
 *
 * @param <T>
 */
public class Vector<T> extends ArrayList<T> {
	void walk(Walker<? super T> walker) {
		for (int i = 0; i < this.size(); ++i) {
			walker.onValue(this.get(i));
		}
	}
}
