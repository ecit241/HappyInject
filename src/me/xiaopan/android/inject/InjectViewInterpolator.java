package me.xiaopan.android.inject;

import java.lang.reflect.Field;

import android.util.Log;
import android.view.View;

public class InjectViewInterpolator implements InjectInterpolator{
	private View rootView;
	
	public InjectViewInterpolator(View rootView) {
		this.rootView = rootView;
	}
	
	public void onInject(Field field, Object object){
		try {
			View view = rootView.findViewById(field.getAnnotation(InjectView.class).value());
			if(view != null){
				field.setAccessible(true);
				field.set(object, view);
			}
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), "Inject "+object.getClass().getSimpleName()+"."+field.getName()+" failure");
			e.printStackTrace();
		}
	}
}
