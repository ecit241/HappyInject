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

package me.xiaopan.android.happyinject.app;

import me.xiaopan.android.happyinject.DisableInjector;
import me.xiaopan.android.happyinject.InjectContentView;
import me.xiaopan.android.happyinject.Injector;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * 提供注入功能的ExpandableListActivity
 */
public abstract class InjectExpandableListActivity extends ExpandableListActivity{
	private Injector injector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		
		if(getClass().isAnnotationPresent(FullScreen.class)){
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		
		if(getClass().isAnnotationPresent(NoTitle.class)){
			getWindow().addFlags(Window.FEATURE_NO_TITLE);
		}
		
		if(!getClass().isAnnotationPresent(DisableInjector.class)){
			injector = new Injector(this);
			InjectContentView injectContentView = getClass().getAnnotation(InjectContentView.class);
			if(injectContentView != null && injectContentView.value() > 0){
				setContentView(injectContentView.value());
			}
			injector.injectOtherMembers();
		}
	}
	
	@Override
	public void onContentChanged() {
		super.onContentChanged();
		if(injector != null){
			injector.injectViewMembers();
		}
	}
}