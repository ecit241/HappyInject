package me.xiaopan.android.happyinject.sample.model;

import java.util.List;

import me.xiaopan.android.happyinject.widget.InjectExpandableListAdapter.GetChildList;

/**
 * 产品组
 */
public class ProductGroup implements GetChildList<Product>{
	private String name;	//名称
	
	private List<Product> products;	//产品集合
	
	public ProductGroup(String name, List<Product> products) {
		this.name = name;
		this.products = products;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public List<Product> getChildList() {
		return products;
	}
}
