/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.android.inject.sample;

import me.xiaopan.android.inject.R;
import me.xiaopan.android.inject.InjectContentView;
import me.xiaopan.android.inject.InjectView;
import me.xiaopan.android.inject.app.InjectDialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

@InjectContentView(R.layout.fragment_dialog_test)
public class TestDialogFragment extends InjectDialogFragment {
	@InjectView(R.id.text_dialog)
	private TextView textView;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		textView.setText("启动耗时："+Second.SECOND_CHRONOGRAPH.lap().getIntervalMillis()+"毫秒");
	}
}
