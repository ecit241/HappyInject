package me.xiaopan.android.happyinject;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class InjectResourceInterpolator implements InjectInterpolator {
	private Context context;

	public InjectResourceInterpolator(Context context) {
		this.context = context;
	}

	@Override
	public void onInject(Field field, Object object) {
		InjectResource injectResource = field.getAnnotation(InjectResource.class);
		Class<?> fieldType = field.getType();
		try {
			if(String.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, context.getResources().getString(injectResource.value()));
			}else if(String[].class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, context.getResources().getStringArray(injectResource.value()));
			}else if(Drawable.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, context.getResources().getDrawable(injectResource.value()));
			}else if(int.class.isAssignableFrom(fieldType) || Integer.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, context.getResources().getInteger(injectResource.value()));
			}else if(int[].class.isAssignableFrom(fieldType) || Integer[].class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, context.getResources().getIntArray(injectResource.value()));
			}else if(boolean.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, context.getResources().getBoolean(injectResource.value()));
			}else if(ColorStateList.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, context.getResources().getColorStateList(injectResource.value()));
			}else if(Animation.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, AnimationUtils.loadAnimation(context, injectResource.value()));
			}else if(Movie.class.isAssignableFrom(fieldType)){
				field.setAccessible(true);
				field.set(object, context.getResources().getMovie(injectResource.value()));
			}
//			resources.getAnimation(id)
//			resources.getColor(id)
//			resources.getDimension(id)
//			resources.getDimensionPixelOffset(id)
//			resources.getDimensionPixelSize(id)
//			resources.getDrawableForDensity(id, density)
//			resources.getFraction(id, base, pbase)
//			resources.getIdentifier(name, defType, defPackage)
//			resources.getLayout(id)
//			resources.getMovie(id)
//			resources.getQuantityString(id, quantity)
//			resources.getQuantityString(id, quantity, formatArgs)
//			resources.getQuantityText(id, quantity)
//			resources.getResourceEntryName(resid)
//			resources.getString(id, formatArgs)
//			resources.getText(id)
//			resources.getTextArray(id)
//			resources.getValue(id, outValue, resolveRefs)
//			resources.getValue(name, outValue, resolveRefs)
//			resources.getValueForDensity(id, density, outValue, resolveRefs)
//			resources.getXml(id)
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), "Inject"+object.getClass().getSimpleName()+"."+field.getName()+"failure");
			e.printStackTrace();
		}
	}
}
