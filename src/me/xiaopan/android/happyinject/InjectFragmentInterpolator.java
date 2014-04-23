package me.xiaopan.android.happyinject;

import java.lang.reflect.Field;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class InjectFragmentInterpolator implements InjectInterpolator{
	private FragmentActivity fragmentActivity;
	
	public InjectFragmentInterpolator(FragmentActivity fragmentActivity) {
		super();
		this.fragmentActivity = fragmentActivity;
	}

	@Override
	public void onInject(Field field) {
		InjectFragment injectFragment = field.getAnnotation(InjectFragment.class);
		try {
			if(injectFragment.value() > 0){
				field.setAccessible(true);
				field.set(fragmentActivity, fragmentActivity.getSupportFragmentManager().findFragmentById(injectFragment.value()));
			}else if(injectFragment.tag() != null && !"".equals(injectFragment.tag().trim())){
				field.setAccessible(true);
				field.set(fragmentActivity, fragmentActivity.getSupportFragmentManager().findFragmentByTag(injectFragment.tag()));
			}
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), "Inject"+fragmentActivity.getClass().getSimpleName()+"."+field.getName()+"failure");
			e.printStackTrace();
		}
	}
}
