/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.android.happyinject.sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

public class MainActivity extends FragmentActivity{
	public static final String PARAM_BYTE = "PARAM_BYTE";
	public static final String PARAM_BYTE_ARRAY = "PARAM_BYTE_ARRAY";
	public static final String PARAM_SHORT = "PARAM_SHORT";
	public static final String PARAM_SHORT_ARRAY = "PARAM_SHORT_ARRAY";
	public static final String PARAM_INT = "PARAM_INT";
	public static final String PARAM_INT_ARRAY = "PARAM_INT_ARRAY";
	public static final String PARAM_LONG = "PARAM_LONG";
	public static final String PARAM_LONG_ARRAY = "PARAM_LONG_ARRAY";
	public static final String PARAM_CHAR = "PARAM_CHAR";
	public static final String PARAM_CHAR_ARRAY = "PARAM_CHAR_ARRAY";
	public static final String PARAM_FLOAT = "PARAM_FLOAT";
	public static final String PARAM_FLOAT_ARRAY = "PARAM_FLOAT_ARRAY";
	public static final String PARAM_DOUBLE = "PARAM_DOUBLE";
	public static final String PARAM_DOUBLE_ARRAY = "PARAM_DOUBLE_ARRAY";
	public static final String PARAM_BOOLEAN = "PARAM_BOOLEAN";
	public static final String PARAM_BOOLEAN_ARRAY = "PARAM_BOOLEAN_ARRAY";
	public static final String PARAM_STRING = "PARAM_STRING";
	public static final String PARAM_STRING_ARRAY = "PARAM_STRING_ARRAY";
	public static final String PARAM_STRING_ARRAY_LIST = "PARAM_STRING_ARRAY_LIST";
	public static final String PARAM_CHAR_SEQUENCE = "PARAM_CHAR_SEQUENCE";
	public static final String PARAM_CHAR_SEQUENCE_ARRAY = "PARAM_CHAR_SEQUENCE_ARRAY";
	public static final String PARAM_STRING_JSON = "PARAM_STRING_JSON";
	public static final String KEY_BOOLEAN = "KEY_BOOLEAN";
	public static final String KEY_FLOAT = "KEY_FLOAT";
	public static final String KEY_INT = "KEY_INT";
	public static final String KEY_LONG = "KEY_LONG";
	public static final String KEY_STRING = "KEY_STRING";
	public static final String KEY_STRING_SET = "KEY_STRING_SET";
	public static final String KEY_JSON = "KEY_JSON";
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = new ListView(getBaseContext());
		setContentView(listView);
		
		// 先在SharedPreferences中放一些数据
		PreferenceUtils.putBoolean(getBaseContext(), KEY_BOOLEAN, true);
		PreferenceUtils.putFloat(getBaseContext(), KEY_FLOAT, 10000f);
		PreferenceUtils.putInt(getBaseContext(), KEY_INT, 2000);
		PreferenceUtils.putLong(getBaseContext(), KEY_LONG, 50000);
		PreferenceUtils.putString(getBaseContext(), KEY_STRING, "Preference String");
		Set<String> stringSet = new HashSet<String>();
		stringSet.add("String Set 1");
		stringSet.add("String Set 2");
		stringSet.add("String Set 3");
		stringSet.add("String Set 4");
		PreferenceUtils.putStringSet(getBaseContext(), KEY_STRING_SET, stringSet);
		MyBean bean2 = new MyBean();
		bean2.setEmail("sky@xiaopan.me2");
		bean2.setName("小潘2");
		bean2.setSex("男2");
		PreferenceUtils.putObject(getBaseContext(), KEY_JSON, bean2);
		
		// 然后初始化列表
		String[] items = new String[]{"注入功能测试", "非注入功能测试", "FragmentDialog测试", "InjectAdapter", "InjectExpandableListAdapter"};
		listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, android.R.id.text1, items));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position <= 2){
					Bundle bundle = new Bundle();
					bundle.putBoolean(MainActivity.PARAM_BOOLEAN, true);
					bundle.putBooleanArray(MainActivity.PARAM_BOOLEAN_ARRAY, new boolean[]{true, false, true});
					bundle.putByte(MainActivity.PARAM_BYTE, (byte) 110);
					bundle.putByteArray(MainActivity.PARAM_BYTE_ARRAY, new byte[]{111, 112, 113});
					bundle.putChar(MainActivity.PARAM_CHAR, 'R');
					bundle.putCharArray(MainActivity.PARAM_CHAR_ARRAY, new char[]{'c', 'h', 'a', 'r'});
					bundle.putCharSequence(MainActivity.PARAM_CHAR_SEQUENCE, "CharSequence");
					bundle.putCharSequenceArray(MainActivity.PARAM_CHAR_SEQUENCE_ARRAY, new CharSequence[]{"Char", " ", "Sequence"});
					bundle.putDouble(MainActivity.PARAM_DOUBLE, 12.00d);
					bundle.putDoubleArray(MainActivity.PARAM_DOUBLE_ARRAY, new double[]{12.01d, 12.02d, 12.03d});
					bundle.putFloat(MainActivity.PARAM_FLOAT, 13.00f);
					bundle.putFloatArray(MainActivity.PARAM_FLOAT_ARRAY, new float[]{13.01f, 13.02f, 13.03f});
					bundle.putInt(MainActivity.PARAM_INT, 120);
					bundle.putIntArray(MainActivity.PARAM_INT_ARRAY, new int[]{121, 122, 123,});
					bundle.putLong(MainActivity.PARAM_LONG, 12345);
					bundle.putLongArray(MainActivity.PARAM_LONG_ARRAY, new long[]{12346, 12347, 12348});
					bundle.putShort(MainActivity.PARAM_SHORT, (short) 2);
					bundle.putShortArray(MainActivity.PARAM_SHORT_ARRAY, new short[]{3, 4, 5});
					bundle.putString(MainActivity.PARAM_STRING, "String");
					bundle.putStringArray(MainActivity.PARAM_STRING_ARRAY, new String[]{"String1", "String2", "String3"});
					
					// 将一个对象转换成JSON字符串放进Bundle中
					MyBean bean = new MyBean();
					bean.setEmail("sky@xiaopan.me");
					bean.setName("小潘");
					bean.setSex("男");
					bundle.putString(PARAM_STRING_JSON, new Gson().toJson(bean));
					
					// 放一个字符串列表进去
					ArrayList<String> stringList = new ArrayList<String>();
					stringList.add("ArrayList String 1");
					stringList.add("ArrayList String 2");
					stringList.add("ArrayList String 3");
					bundle.putStringArrayList(MainActivity.PARAM_STRING_ARRAY_LIST, stringList);
					switch(position){
					case 0 : 
						Second.SECOND_CHRONOGRAPH.lap(); 
						Intent intent = new Intent(getBaseContext(), InjectTestActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					case 1 : 
						Second.SECOND_CHRONOGRAPH.lap(); 
						Intent intent2 = new Intent(getBaseContext(), NormalActivity.class);
						intent2.putExtras(bundle);
						startActivity(intent2);
						break;
					case 2 : 
						Second.SECOND_CHRONOGRAPH.lap(); 
						new TestDialogFragment().show(getSupportFragmentManager(), ""); 
						break;
					}
				} else {
					Class<?> targetClass = null;
					if(position == 3){
						targetClass = InjectAdapterActivity.class;
					}else if(position == 4){
						targetClass = InjectExpandableListAdapterActivity.class;
					}
					if(targetClass != null){
						startActivity(new Intent(getBaseContext(), targetClass));
					}
				}
			}
		});
	}
}
