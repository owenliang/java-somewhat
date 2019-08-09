package cc.yuerblog.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.yuerblog.annotation.Uri;

public class Main {
	public static void main(String[] args) {
		App app = new App();
		
		Main.handleRoute(app, "/user/owenliang/info");
		Main.handleRoute(app, "/product/110/info");
	}
	
	public static void handleRoute(App app, String uri) {
		// 拆分uri
		String[] uriArr = uri.split("/");
		// System.out.println(Arrays.toString(uriArr));
		
		// 遍历所有方法
		Method[] methods = app.getClass().getMethods();
		for (Method method : methods) {
			// 获取Uri注解
			Uri anno = method.getAnnotation(Uri.class);
			if (anno == null) {
				continue;
			}
			// 得到path
			String path = anno.path();
			// 拆分path
			String[] pathArr = path.split("/");
			// 匹配
			if (pathArr.length != uriArr.length) {
				continue;
			}
			boolean isMatch = true;
			ArrayList<String> catchParams = new ArrayList<>();
			for (int i = 0; i < pathArr.length; ++i) {
				if (pathArr[i].startsWith("{")) {	// 参数捕获
					String paramValue = uriArr[i];	// 捕获参数值
					catchParams.add(paramValue);
				} else if (!pathArr[i].equals(uriArr[i])) {
					isMatch = false;
					break;
				}
			}
			// 路由匹配
			if (isMatch) {
				// 进行参数校验
				boolean canCall = true;
				
				// 反射方法参数
				Parameter[] params = method.getParameters();
				if (params.length == catchParams.size()) {		// 参数数量相等
					// 处理每个参数
					List prepareParams = new ArrayList();
					for (int i = 0; i < params.length; ++i) {
						System.out.println(params[i].getType() + ":" + Number.class.isAssignableFrom(params[i].getType()));
						// 根据类型进行转换
						if (params[i].getType().isAssignableFrom(Number.class)) {	// 数字参数
							prepareParams.add(Double.parseDouble(catchParams.get(i)));
						} else if (params[i].getType().isAssignableFrom(String.class)) {	// 字符串参数
							prepareParams.add(catchParams.get(i));
						} else { // 不支持的类型
							canCall = false;
							break;
						}
					}
					if (canCall) {
						// 执行对应的处理函数
						try {
							method.invoke(app, prepareParams.toArray());
						} catch (Exception e) {	
							System.out.println(e);
						}
					}
				}
				break;
			}
		}
	}
}
