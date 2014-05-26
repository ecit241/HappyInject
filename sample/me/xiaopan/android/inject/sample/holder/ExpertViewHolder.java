package me.xiaopan.android.inject.sample.holder;

import me.xiaopan.android.inject.R;
import me.xiaopan.android.inject.InjectContentView;
import me.xiaopan.android.inject.InjectView;
import me.xiaopan.android.inject.sample.model.Expert;
import me.xiaopan.android.inject.widget.InjectAdapter;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

@InjectContentView(R.layout.list_item_expert)
public class ExpertViewHolder implements InjectAdapter.ViewHolder<Expert>{
    @InjectView(R.id.text_expertItem_name) private TextView nameText;
    @InjectView(R.id.text_expertItem_major) private TextView majorText;
    @InjectView(R.id.button_expertItem_askQuestion) public Button askQuestion;

    @Override
    public void setValues(Context context, int position, Expert item) {
        nameText.setText(item.getName());
        majorText.setText(item.getMajor());
        askQuestion.setTag(item);
    }

	public Button getAskQuestion() {
		return askQuestion;
	}
}