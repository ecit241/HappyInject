package me.xiaopan.android.inject.sample.holder;

import me.xiaopan.android.inject.R;
import me.xiaopan.android.inject.InjectContentView;
import me.xiaopan.android.inject.InjectView;
import me.xiaopan.android.inject.sample.model.Product;
import me.xiaopan.android.inject.widget.InjectExpandableListAdapter.ChildViewHolder;
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
