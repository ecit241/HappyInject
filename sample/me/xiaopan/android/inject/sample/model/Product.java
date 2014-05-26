package me.xiaopan.android.inject.sample.model;

/**
 * 产品
 */
public class Product {
    private String name = null;//名称
    private String viewCount = "0"; // 已查看次数
    
    public Product(String name, String viewCount) {
		this.name = name;
		this.viewCount = viewCount;
	}

	public String getName() {
		return name;
	}

	public String getViewCount() {
		return viewCount;
	}
}
