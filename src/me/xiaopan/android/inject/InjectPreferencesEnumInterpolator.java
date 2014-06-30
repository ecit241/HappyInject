package me.xiaopan.android.inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * 注入SharedPreferences中的Enum对象
 */
public class InjectPreferencesEnumInterpolator implements InjectInterpolator {
	private Context context;
	
	public InjectPreferencesEnumInterpolator(Context context) {
		this.context = context;
	}

	@Override
	public void onInject(Field field, Object object) {
		InjectPreferencesEnum injectPreferencesEnum = field.getAnnotation(InjectPreferencesEnum.class);
		if(injectPreferencesEnum.value() != null && !"".equals(injectPreferencesEnum.value().trim())){
			SharedPreferences sharedPreferences = null;
			if(injectPreferencesEnum.sharedPreferencesName() != null && !"".equals(injectPreferencesEnum.sharedPreferencesName().trim())){
				sharedPreferences = context.getSharedPreferences(injectPreferencesEnum.sharedPreferencesName(), injectPreferencesEnum.mode());
			}else{
				sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
			}
			try {
				String valueObject = sharedPreferences.getString(injectPreferencesEnum.value(), null);
				if(valueObject != null && !"".equals(valueObject.trim())){
					Method valueOfMethod = field.getType().getMethod("valueOf", new Class<?>[]{String.class});
					Object fieldObject = valueOfMethod.invoke(object, new Object[]{valueObject});
					if(fieldObject != null){
						field.setAccessible(true);
						field.set(object, fieldObject);
					}
				}
			} catch (Exception e) {
				Log.w(getClass().getSimpleName(), "Inject "+object.getClass().getSimpleName()+"."+field.getName()+" failure");
				e.printStackTrace();
			}
		}
	}
}
