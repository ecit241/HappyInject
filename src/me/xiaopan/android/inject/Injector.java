package me.xiaopan.android.inject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * 注入器
 */
public class Injector {
	private Object injectObject;
	private List<Field> extraFields;
	private List<Field> viewFields;
	private List<Field> knownFields;
	private List<Field> resourceFields;
	private List<Field> extraJsonFields;
	private List<Field> extraEnumFields;
	private List<Field> fragmentFields;
	private List<Field> preferencesFields;
	private List<Field> preferencesJsonFields;
	private List<Field> preferencesEnumFields;
	
	public Injector(Object injectObject){
		setInjectObject(injectObject);
	}
	
	public void setInjectObject(Object injectObject){
		if(injectObject == null){
			new NullPointerException("injectObject is null").printStackTrace();
			return;
		}
		
		this.injectObject = injectObject;
		extraFields = null;
		viewFields = null;
		knownFields = null;
		resourceFields = null;
		extraJsonFields = null;
		extraEnumFields = null;
		fragmentFields = null;
		preferencesFields = null;
		preferencesJsonFields = null;
		preferencesEnumFields = null;
		
		// 将字段分组
		int modifiers;
		Class<?> classs = injectObject.getClass();
		while(true){
			if(classs != null){
				for(Field field : classs.getDeclaredFields()){
					modifiers = field.getModifiers();
					if(!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers)){
						if(field.isAnnotationPresent(InjectExtra.class)){
							if(extraFields == null){
								extraFields = new LinkedList<Field>();
							}
							extraFields.add(field);
						}else if(field.isAnnotationPresent(InjectView.class)){
							if(viewFields == null){
								viewFields = new LinkedList<Field>();
							}
							viewFields.add(field);
						}else if(field.isAnnotationPresent(Inject.class)){
							if(knownFields == null){
								knownFields = new LinkedList<Field>();
							}
							knownFields.add(field);
						}else if(field.isAnnotationPresent(InjectResource.class)){
							if(resourceFields == null){
								resourceFields = new LinkedList<Field>();
							}
							resourceFields.add(field);
						}else if(field.isAnnotationPresent(InjectExtraJson.class)){
							if(extraJsonFields == null){
								extraJsonFields = new LinkedList<Field>();
							}
							extraJsonFields.add(field);
						}else if(field.isAnnotationPresent(InjectExtraEnum.class)){
							if(extraEnumFields == null){
								extraEnumFields = new LinkedList<Field>();
							}
							extraEnumFields.add(field);
						}else if(field.isAnnotationPresent(InjectFragment.class)){
							if(fragmentFields == null){
								fragmentFields = new LinkedList<Field>();
							}
							fragmentFields.add(field);
						}else if(field.isAnnotationPresent(InjectPreferences.class)){
							if(preferencesFields == null){
								preferencesFields = new LinkedList<Field>();
							}
							preferencesFields.add(field);
						}else if(field.isAnnotationPresent(InjectPreferencesJson.class)){
							if(preferencesJsonFields == null){
								preferencesJsonFields = new LinkedList<Field>();
							}
							preferencesJsonFields.add(field);
						}else if(field.isAnnotationPresent(InjectPreferencesEnum.class)){
							if(preferencesEnumFields == null){
								preferencesEnumFields = new LinkedList<Field>();
							}
							preferencesEnumFields.add(field);
						}
					}
				}
				
				if(classs.isAnnotationPresent(InjectParentMember.class)){
					classs = classs.getSuperclass();
				}else{
					break;
				}
			}else{
				break;
			}
		}
	}
	
	/**
	 * 注入View字段
	 * @param rootView
	 */
	public void injectViewMembers(View rootView){
		if(injectObject == null || rootView == null || viewFields == null || viewFields.size() <= 0){
			return;
		}
		
		InjectViewInterpolator injectViewInterpolator = new InjectViewInterpolator(rootView);
		for(Field field : viewFields){
			injectViewInterpolator.onInject(field, injectObject);
		}
	}
	
	/**
	 * 注入Fragment字段
	 * @param fragmentManager
	 */
	public void injectFragmentMembers(FragmentManager fragmentManager){
		if(injectObject == null || fragmentManager == null || fragmentFields == null || fragmentFields.size() <= 0){
			return;
		}
		
		InjectFragmentInterpolator injectFragmentInterpolator = new InjectFragmentInterpolator(fragmentManager);
		for(Field field : fragmentFields){
			injectFragmentInterpolator.onInject(field, injectObject);
		}
	}
	
	/**
	 * 注入Extra成员
	 * @param bundle
	 */
	public void injectExtraMembers(Bundle bundle){
		if(injectObject == null || bundle == null){
			return;
		}
		
		if(extraFields != null && extraFields.size() > 0){
			InjectExtraInterpolator injectExtraInterpolator = new InjectExtraInterpolator(bundle);
			for(Field field : extraFields){
				injectExtraInterpolator.onInject(field, injectObject);
			}
		}
		
		if(extraJsonFields != null && extraJsonFields.size() > 0){
			InjectExtraJsonInterpolator injectExtraJsonInterpolator = new InjectExtraJsonInterpolator(bundle);
			for(Field field : extraJsonFields){
				injectExtraJsonInterpolator.onInject(field, injectObject);
			}
		}
		
		if(extraEnumFields != null && extraEnumFields.size() > 0){
			InjectExtraEnumInterpolator injectExtraEnumInterpolator = new InjectExtraEnumInterpolator(bundle);
			for(Field field : extraEnumFields){
				injectExtraEnumInterpolator.onInject(field, injectObject);
			}
		}
	}
	
	/**
	 * 注入Resource资源
	 * @param context
	 */
	public void injectResourceMembers(Context context){
		if(injectObject == null || context == null || resourceFields == null || resourceFields.size() <= 0){
			return;
		}
		
		InjectResourceInterpolator injectResourceInterpolator = new InjectResourceInterpolator(context);
		for(Field field : resourceFields){
			injectResourceInterpolator.onInject(field, injectObject);
		}
	}
	
	/**
	 * 注入Known字段
	 * @param context
	 */
	public void injectKnowMembers(Context context){
		if(injectObject == null || context == null || knownFields == null || knownFields.size() <= 0){
			return;
		}
		
		InjectKnownInterpolator injectKnownInterpolator = new InjectKnownInterpolator(context);
		for(Field field : knownFields){
			injectKnownInterpolator.onInject(field, injectObject);
		}
	}
	
	/**
	 * 注入Preference字段
	 * @param context
	 */
	public void injectPreferenceMembers(Context context){
		if(injectObject == null || context == null){
			return;
		}
		
		if(preferencesFields != null && preferencesFields.size() > 0){
			InjectPreferencesInterpolator injectPreferencesInterpolator = new InjectPreferencesInterpolator(context);
			for(Field field : preferencesFields){
				injectPreferencesInterpolator.onInject(field, injectObject);
			}
		}
		
		if(preferencesJsonFields != null && preferencesJsonFields.size() > 0){
			InjectPreferencesJsonInterpolator injectPreferencesJsonInterpolator = new InjectPreferencesJsonInterpolator(context);
			for(Field field : preferencesJsonFields){
				injectPreferencesJsonInterpolator.onInject(field, injectObject);
			}
		}
		
		if(preferencesEnumFields != null && preferencesEnumFields.size() > 0){
			InjectPreferencesEnumInterpolator injectPreferencesEnumInterpolator = new InjectPreferencesEnumInterpolator(context);
			for(Field field : preferencesEnumFields){
				injectPreferencesEnumInterpolator.onInject(field, injectObject);
			}
		}
	}
}