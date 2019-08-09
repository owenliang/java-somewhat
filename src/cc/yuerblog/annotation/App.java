package cc.yuerblog.annotation;
import cc.yuerblog.annotation.Uri;

public class App {
	@Uri(path="/user/{username}/info")
	public void getUserInfo(String username) {
		System.out.println("查找用户:" + username);
	}
	
	@Uri(path="/product/{productID}/info")
	public void getProductInfo(int productID) {
		System.out.println("查找商品:" + productID);
	}
}
