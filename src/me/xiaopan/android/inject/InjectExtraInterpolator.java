package me.xiaopan.android.inject;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.util.Log;

public class InjectExtraInterpolator implements InjectInterpolator {
	private Bundle bundle;
	
	public InjectExtraInterpolator(Bundle bundle) {
		this.bundle = bundle;
	}

	@Override
	public void onInject(Field field, Object object) {
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
					Log.w(getClass().getSimpleName(), "Inject "+object.getClass().getSimpleName()+"."+field.getName()+" failure");
					e.printStackTrace();
				}
			}
		}
	}
}
