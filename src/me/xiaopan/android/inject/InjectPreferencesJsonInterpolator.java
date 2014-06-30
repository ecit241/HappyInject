package me.xiaopan.android.inject;

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
	private Context context;
	
	public InjectPreferencesJsonInterpolator(Context context) {
		this.context = context;
	}

	@Override
	public void onInject(Field field, Object object) {
		InjectPreferencesJson injectPreferencesJson = field.getAnnotation(InjectPreferencesJson.class);
		if(injectPreferencesJson.value() != null && !"".equals(injectPreferencesJson.value().trim())){
			SharedPreferences sharedPreferences = null;
			if(injectPreferencesJson.sharedPreferencesName() != null && !"".equals(injectPreferencesJson.sharedPreferencesName().trim())){
				sharedPreferences = context.getSharedPreferences(injectPreferencesJson.sharedPreferencesName(), injectPreferencesJson.mode());
			}else{
				sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
			}
			try {
				String valueObject = sharedPreferences.getString(injectPreferencesJson.value(), null);
				if(valueObject != null && !"".equals(valueObject.trim())){
					Gson gson = null;
					if(injectPreferencesJson.withoutExposeAnnotation()){
						gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
					}else{
						gson = new Gson();
					}
					field.setAccessible(true);
					field.set(object, gson.fromJson(valueObject, field.getType()));
				}
			} catch (Exception e) {
				Log.w(getClass().getSimpleName(), "Inject "+object.getClass().getSimpleName()+"."+field.getName()+" failure");
				e.printStackTrace();
			}
		}
	}
}
