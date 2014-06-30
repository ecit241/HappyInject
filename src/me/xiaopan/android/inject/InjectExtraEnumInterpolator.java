package me.xiaopan.android.inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.os.Bundle;
import android.util.Log;

public class InjectExtraEnumInterpolator implements InjectInterpolator {
	private Bundle bundle;
	
	public InjectExtraEnumInterpolator(Bundle bundle) {
		this.bundle = bundle;
	}

	@Override
	public void onInject(Field field, Object object) {
		if(bundle != null){
			InjectExtraEnum injectExtraEnum = field.getAnnotation(InjectExtraEnum.class);
			if(injectExtraEnum.value() != null && !"".equals(injectExtraEnum.value().trim())){
				try {
					String valueObject = bundle.getString(injectExtraEnum.value());
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
}
