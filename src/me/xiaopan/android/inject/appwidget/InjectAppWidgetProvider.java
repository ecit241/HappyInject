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

package me.xiaopan.android.inject.appwidget;

import me.xiaopan.android.inject.DisableInjector;
import me.xiaopan.android.inject.Injector;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

/**
 * 提供注入功能的提供注入功能的AppWidgetProvider
 */
public abstract class InjectAppWidgetProvider extends AppWidgetProvider {
	private Injector injector;
	
	public InjectAppWidgetProvider() {
		if(!getClass().isAnnotationPresent(DisableInjector.class)){
			injector = new Injector(this);
		}
	}
	
	@Override
	public final void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		if(injector != null){
			injector.injectKnowMembers(context);
			injector.injectPreferenceMembers(context);
			injector.injectResourceMembers(context);
		}
	}

	public Injector getInjector() {
		return injector;
	}
}
