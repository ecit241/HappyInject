package me.xiaopan.android.happyinject;

import java.lang.reflect.Field;

import android.support.v4.app.FragmentManager;
import android.util.Log;

public class InjectFragmentInterpolator implements InjectInterpolator{
	private FragmentManager fragmentManager;
	
	public InjectFragmentInterpolator(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	@Override
	public void onInject(Field field, Object object) {
		InjectFragment injectFragment = field.getAnnotation(InjectFragment.class);
		try {
			if(injectFragment.value() > 0){
				field.setAccessible(true);
				field.set(object, fragmentManager.findFragmentById(injectFragment.value()));
			}else if(injectFragment.tag() != null && !"".equals(injectFragment.tag().trim())){
				field.setAccessible(true);
				field.set(object, fragmentManager.findFragmentByTag(injectFragment.tag()));
			}
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), "Inject "+object.getClass().getSimpleName()+"."+field.getName()+" failure");
			e.printStackTrace();
		}
	}
}
