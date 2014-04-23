package me.xiaopan.android.happyinject;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.util.Log;

public class InjectExtraInterpolator implements InjectInterpolator {
	private Object object;
	private Bundle bundle;
	
	public InjectExtraInterpolator(Object object, Bundle bundle) {
		this.object = object;
		this.bundle = bundle;
	}

	@Override
	public void onInject(Field field) {
		if(bundle != null){
			InjectExtra injectExtra = field.getAnnotation(InjectExtra.class);
			if(injectExtra.value() != null && !"".equals(injectExtra.value().trim())){
				try {
					Object valueObject = bundle.get(injectExtra.value());
					if(valueObject != null){
						field.setAccessible(true);
						field.set(object, valueObject);
					}
				} catch (Exception e) {
					Log.w(getClass().getSimpleName(), "Inject"+object.getClass().getSimpleName()+"."+field.getName()+"failure");
					e.printStackTrace();
				}
			}
		}
	}
}
