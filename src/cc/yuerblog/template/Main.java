package cc.yuerblog.template;

public class Main {
	public static void main(String[] args) {
		Vector<Integer> vector = new Vector<>();
		
		vector.add(1);
		vector.add(2);
		
		// 基于callback遍历：遍历vector, 要求回调函数能兼容Integer向上转型即可
		vector.walk(new Walker<Number>() {
			@Override
			public void onValue(Number value) {
				System.out.println(value.intValue());
			}
		});
		
		// 直接遍历：要求Vector中元素是Number的子类
		Main.walk(vector);
	}
	
	public static void walk(Vector<? extends Number> vector) {
		for (int i = 0; i < vector.size(); ++i) {
			Number num = vector.get(i);
			System.out.println(num.intValue());
		}
	}
}
