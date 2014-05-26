package me.xiaopan.android.inject.sample.holder;

import me.xiaopan.android.inject.R;
import me.xiaopan.android.inject.InjectContentView;
import me.xiaopan.android.inject.InjectView;
import me.xiaopan.android.inject.sample.model.ProductGroup;
import me.xiaopan.android.inject.widget.InjectExpandableListAdapter.GroupViewHolder;
import android.content.Context;
import android.widget.TextView;

@InjectContentView(R.layout.list_item_product_group)
public class ProductGroupViewHolder implements GroupViewHolder<ProductGroup> {
	@InjectView(R.id.text_productGroupItem_title) private TextView titleTextView;
	
	@Override
	public void setValues(Context arg0, int position, boolean arg2, ProductGroup productGroup) {
		titleTextView.setText(productGroup.getName());
	}
}