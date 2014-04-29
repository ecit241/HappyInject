package me.xiaopan.android.happyinject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 注入SharedPreferences中的参数
 */
public class InjectPreferencesInterpolator implements InjectInterpolator {
	private Context context;
	
	public InjectPreferencesInterpolator(Context context) {
		this.context = context;
	}

	@Override
	public void onInject(Field field, Object object) {
		InjectPreference injectPreference = field.getAnnotation(InjectPreference.class);
		Class<?> fieldType = field.getType();
		try {
			if(boolean.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, getSharedPreferences(injectPreference).getBoolean(injectPreference.value(), injectPreference.booleanDefaultValue()));
			}else if(float.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, getSharedPreferences(injectPreference).getFloat(injectPreference.value(), injectPreference.floatDefaultValue()));
			}else if(int.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, getSharedPreferences(injectPreference).getInt(injectPreference.value(), injectPreference.intDefaultValue()));
			}else if(long.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, getSharedPreferences(injectPreference).getLong(injectPreference.value(), injectPreference.longDefaultValue()));
			}else if(String.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, getSharedPreferences(injectPreference).getString(injectPreference.value(), "".equals(injectPreference.stringDefaultValue())?null:injectPreference.stringDefaultValue()));
			}else if(Set.class.isAssignableFrom(fieldType)){
				Class<?> first = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
				if(String.class.isAssignableFrom(first)){
					field.setAccessible(true);
					field.set(object, getStringSet(getSharedPreferences(injectPreference), injectPreference.value(), null));
				}
			}
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), "Inject"+object.getClass().getSimpleName()+"."+field.getName()+"failure");
			e.printStackTrace();
		}
	}
	
	private SharedPreferences getSharedPreferences(InjectPreference injectPreference){
		SharedPreferences sharedPreferences;
		if(injectPreference.sharedPreferencesName() != null && !"".equals(injectPreference.sharedPreferencesName().trim())){
			sharedPreferences = context.getSharedPreferences(injectPreference.sharedPreferencesName(), injectPreference.mode());
		}else{
			sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		}
		return sharedPreferences;
	}
	
	/**
	 * 从指定的Preference中取出一个Set<String>，如果当前系统的SDK版本小于11，则会先取出JSON字符串然后再转换成Set<String>
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static Set<String> getStringSet(SharedPreferences preferences, String key, Set<String> defaultVaule){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			return preferences.getStringSet(key, defaultVaule);
		}else{
			Set<String> strings = getObject(preferences, key, new TypeToken<Set<String>>(){}.getType());
			if(strings == null){
				strings = defaultVaule;
			}
			return strings;
		}
	}
	
	/**
	 * 从指定的Preference中取出一个对象
	 */
	public static <T> T getObject(SharedPreferences preferences, String key, Type typeofT){
		String configJson = preferences.getString(key, null);
		if(configJson != null && !"".equals(configJson.trim())){
			return new Gson().fromJson(configJson, typeofT);
		}else{
			return null;
		}
	}
}
