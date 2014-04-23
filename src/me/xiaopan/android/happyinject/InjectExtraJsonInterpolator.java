package me.xiaopan.android.happyinject;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InjectExtraJsonInterpolator implements InjectInterpolator {
	private Object object;
	private Bundle bundle;
	
	public InjectExtraJsonInterpolator(Object object, Bundle bundle) {
		this.object = object;
		this.bundle = bundle;
	}

	@Override
	public void onInject(Field field) {
		if(bundle != null){
			InjectExtraJson injectExtraJson = field.getAnnotation(InjectExtraJson.class);
			if(injectExtraJson.value() != null && !"".equals(injectExtraJson.value().trim())){
				try {
					String valueObject = bundle.getString(injectExtraJson.value());
					if(valueObject != null && !"".equals(valueObject.trim())){
						field.setAccessible(true);
						field.set(object, injectExtraJson.withoutExposeAnnotation()?new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create():new Gson().fromJson(valueObject, field.getType()));
					}
				} catch (Exception e) {
					Log.w(getClass().getSimpleName(), "Inject"+object.getClass().getSimpleName()+"."+field.getName()+"failure");
					e.printStackTrace();
				}
			}
		}
	}
}
