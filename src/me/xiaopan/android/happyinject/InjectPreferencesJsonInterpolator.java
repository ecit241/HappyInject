package me.xiaopan.android.happyinject;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 注入SharedPreferences中的Json对象
 */
public class InjectPreferencesJsonInterpolator implements InjectInterpolator {
	private Object object;
	private Context context;
	
	public InjectPreferencesJsonInterpolator(Object object, Context context) {
		this.object = object;
		this.context = context;
	}

	@Override
	public void onInject(Field field) {
		InjectPreferenceJson injectPreference = field.getAnnotation(InjectPreferenceJson.class);
		if(injectPreference.value() != null && !"".equals(injectPreference.value().trim())){
			SharedPreferences sharedPreferences = null;
			if(injectPreference.sharedPreferencesName() != null && !"".equals(injectPreference.sharedPreferencesName().trim())){
				sharedPreferences = context.getSharedPreferences(injectPreference.sharedPreferencesName(), injectPreference.mode());
			}else{
				sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
			}
			try {
				String valueObject = sharedPreferences.getString(injectPreference.value(), null);
				if(valueObject != null && !"".equals(valueObject.trim())){
					Gson gson = null;
					if(injectPreference.withoutExposeAnnotation()){
						gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
					}else{
						gson = new Gson();
					}
					field.setAccessible(true);
					field.set(object, gson.fromJson(valueObject, field.getType()));
				}
			} catch (Exception e) {
				Log.w(getClass().getSimpleName(), "Inject"+object.getClass().getSimpleName()+"."+field.getName()+"failure");
				e.printStackTrace();
			}
		}
	}
}
