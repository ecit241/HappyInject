package me.xiaopan.android.inject.sample;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.android.inject.app.InjectListActivity;
import me.xiaopan.android.inject.sample.holder.ExpertViewHolder;
import me.xiaopan.android.inject.sample.model.Expert;
import me.xiaopan.android.inject.widget.BindingEventListener;
import me.xiaopan.android.inject.widget.InjectAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class InjectAdapterActivity extends InjectListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getListView().setAdapter(new InjectAdapter<Expert, ExpertViewHolder>(getBaseContext(), ExpertViewHolder.class, createData(50), new BindingEventListener<ExpertViewHolder>() {
			@Override
			public void bindingEvent(final ExpertViewHolder expertViewHolder) {
				expertViewHolder.getAskQuestion().setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(v.getTag() != null && v.getTag() instanceof Expert){
							Toast.makeText(getBaseContext(), ((Expert) v.getTag()).getName(), Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		}));
	}
	
	private List<Expert> createData(int size){
		List<Expert> experts = new ArrayList<Expert>(size);
		for(int w = 0; w < size; w++){
			experts.add(new Expert("专家"+(w+1), "畜牧业"+(w+1)));
		}
		return experts;
	}
}