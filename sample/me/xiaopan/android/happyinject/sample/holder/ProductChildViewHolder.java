package me.xiaopan.android.happyinject.sample.holder;

import me.xiaopan.android.happyinject.InjectContentView;
import me.xiaopan.android.happyinject.InjectView;
import me.xiaopan.android.happyinject.R;
import me.xiaopan.android.happyinject.sample.model.Product;
import me.xiaopan.android.happyinject.widget.InjectExpandableListAdapter.ChildViewHolder;
import android.content.Context;
import android.widget.TextView;

@InjectContentView(R.layout.list_item_product_child)
public class ProductChildViewHolder implements ChildViewHolder<Product>{
    @InjectView(R.id.text_productChildItem_name) private TextView nameTextView;
    @InjectView(R.id.text_productChildItem_viewCount) private TextView viewCountTextView;

	@Override
	public void setValues(Context context, int groupPosition, int childPosition, boolean arg3, Product product) {
		nameTextView.setText(product.getName());
        viewCountTextView.setText(product.getViewCount());
	}
}
