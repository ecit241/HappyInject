package me.xiaopan.android.inject;

import java.lang.reflect.Field;

public interface InjectInterpolator {
	public void onInject(Field field, Object object);
}
