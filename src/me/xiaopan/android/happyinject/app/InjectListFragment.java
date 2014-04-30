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
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 提供注入功能的ListFragment
 */
public class InjectListFragment extends ListFragment{
	private Injector injector;
	
	public InjectListFragment() {
		if(!getClass().isAnnotationPresent(DisableInjector.class)){
			injector = new Injector(this);
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(injector != null){
			injector.injectExtraMembers(getArguments());
			injector.injectKnowMembers(getActivity().getBaseContext());
			injector.injectPreferenceMembers(getActivity().getBaseContext());
			injector.injectResourceMembers(getActivity().getBaseContext());
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(injector != null){
			InjectContentView injectContentView = getClass().getAnnotation(InjectContentView.class); 
			return injectContentView != null?inflater.inflate(injectContentView.value(), null):null;
		}else{
			return null;
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if(injector != null){
			injector.injectViewMembers(getView());
		}
	}

	public Injector getInjector() {
		return injector;
	}
}