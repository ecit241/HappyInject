package me.xiaopan.android.inject.sample;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.android.inject.app.InjectExpandableListActivity;
import me.xiaopan.android.inject.sample.holder.ProductChildViewHolder;
import me.xiaopan.android.inject.sample.holder.ProductGroupViewHolder;
import me.xiaopan.android.inject.sample.model.Product;
import me.xiaopan.android.inject.sample.model.ProductGroup;
import me.xiaopan.android.inject.widget.InjectExpandableListAdapter;
import android.os.Bundle;

public class InjectExpandableListAdapterActivity extends InjectExpandableListActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getExpandableListView().setAdapter(new InjectExpandableListAdapter<ProductGroup, Product, ProductGroupViewHolder, ProductChildViewHolder>(getBaseContext(), ProductGroupViewHolder.class, ProductChildViewHolder.class, createData(30)));
	}
	
	private List<ProductGroup> createData(int groupSize){
		List<ProductGroup> experts = new ArrayList<ProductGroup>(groupSize);
		for(int w = 0; w < groupSize; w++){
			List<Product> products = new ArrayList<Product>(w+1);
			for(int s = 0, length = w+1; s < length; s++){
				products.add(new Product("产品组"+(w+1)+"中的产品"+(s+1), String.valueOf(s+1)));
			}
			experts.add(new ProductGroup("产品组"+(w+1), products));
		}
		return experts;
	}
}