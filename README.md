# ![Logo](https://github.com/xiaopansky/Android-HappyInject/raw/master/res/drawable-mdpi/ic_launcher.png) Android-HappyInject

Android-HappyInject是用在Android上的一个注入类库

##Features
>* 可注入Layout、View、Resource、Bundle Extra、Service、SharedPreferences、Fragment；
>* 提供了一系列的InjectActivity、InjectFragment、InjectService、InjectAppWidgetProvider、InjectAsyncTaskLoader、InjectBroadcastReceiver、InjectContentProvider、InjectLoader等超类，继承后可以使用注入功能；
>* 特别提供了InjectAdapter、InjectExpandableListAdapter通过注入来避免创建新的Adapter

##Usage
####目前可继承并使用注入功能的超类分以下四类

Activity：
>* InjectAccountAuthenticatorActivity
>* InjectActionBarActivity
>* InjectActivity
>* InjectActivityGroup
>* InjectExpandableListActivity
>* InjectFragmentActivity
>* InjectLauncherActivity
>* InjectListActivity
>* InjectMapActivity
>* InjectPreferenceActivity
>* InjectTabActivity

Fragment：
>* InjectFragment
>* InjectDialogFragment
>* InjectListFragment

Service：
>* InjectIntentService
>* InjectService

BroadcastRecevier：
>* InjectBroadcastReceiver

ContextProvidet：
>* InjectAppWidgetProvider
>* InjectContentProvider

Loader：
>* InjectAsyncTaskLoader
>* InjectLoader

####可使用的注解有以下几种：
>* DisableInjector：禁用注入功能。因为注解功能默认是开启的，如果你不想使用注入的话就可是使用此注解来禁用注入功能；
>* Inject：注入系统服务（通过getService()的各种Manager）或者ShardPreferences；在注入ShardPreferences时你可以通过sharedPreferencesName参数指定名称，不指定时将注入默认的ShardPreferences；
>* InjectContentView：注入内容视图。适用于以下场合：
    1. Activity
    2. Fragment
    3. InjectAdapter.ViewHolder
    3. InjectExpandableListAdapter.GroupViewHolder
    3. InjectExpandableListAdapter.ChildViewHolder
>* InjectExtra：注入Bundle中的参数。适用于以下场合：
    1. Activity：Bundle来自getIntent().getExtras()
    2. Fragment：Bundle来自getArguments()
    3. BroadcastReceiver：Bundle来自onReceive()方法中intent参数的getExtras()
>* InjectExtraJson：将Bundle中的字符串通过Gson转换成对象
>* InjectFragment：注入Fragment。只支持FragmentActivity中Fragment类型的字段；
>* InjectParentMember：注入父类的成员变量。默认是不注入父类的成员变量的；
>* InjectPreference：注入SharedPreferences中的参数。SharedPreferencees的名称可通过sharedPreferencesName参数指定，不指定时将从默认的SharedPreferencees中获取参数；
>* InjectPreferenceJson：将SharedPreferences中的字符串通过Gson转换成对象
>* InjectResource：注入资源。适用于以下场合：
    1. boolean
    2. String
    3. String[]
    4. Integer
    5. Integer[]
    6. Drawable
    7. ColorStateList
    8. Animation
    9. Movie
>* InjectView：注入View。适用于以下场合：
    1. Activity
    2. Fragment
    3. InjectAdapter.ViewHolder
    3. InjectExpandableListAdapter.GroupViewHolder
    3. InjectExpandableListAdapter.ChildViewHolder
    
####示例
```java
@InjectContentView(R.layout.activity_main)
public class MainActivity extends InjectFragmentActivity{
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

    @InjectView(R.id.text_main1) private TextView textView1;
    @InjectView(R.id.text_main2) private TextView textView2;
    @InjectView(R.id.text_main3) private TextView textView3;
    @InjectView(R.id.text_main4) private TextView textView4;
    @InjectView(R.id.text_main5) private TextView textView5;
    
    @InjectExtra(MainActivity.PARAM_BYTE) private byte byteField;
    @InjectExtra(MainActivity.PARAM_BYTE_ARRAY) private byte[] byteFields;
    @InjectExtra(MainActivity.PARAM_SHORT) private short shortField;
    @InjectExtra(MainActivity.PARAM_SHORT_ARRAY) private short[] shortFields;
    @InjectExtra(MainActivity.PARAM_INT) private int intField;
    @InjectExtra(MainActivity.PARAM_INT_ARRAY) private int[] intFields;
    @InjectExtra(MainActivity.PARAM_LONG) private long longField;
    @InjectExtra(MainActivity.PARAM_LONG_ARRAY) private long[] longFields;
    @InjectExtra(MainActivity.PARAM_CHAR) private char charField;
    @InjectExtra(MainActivity.PARAM_CHAR_ARRAY) private char[] charFields;
    @InjectExtra(MainActivity.PARAM_FLOAT) private float floatField;
    @InjectExtra(MainActivity.PARAM_FLOAT_ARRAY) private float[] floatFields;
    @InjectExtra(MainActivity.PARAM_DOUBLE) private double doubleField;
    @InjectExtra(MainActivity.PARAM_DOUBLE_ARRAY) private double[] doubleFields;
    @InjectExtra(MainActivity.PARAM_BOOLEAN) private boolean booleanField;
    @InjectExtra(MainActivity.PARAM_BOOLEAN_ARRAY) private boolean[] booleanFields;
    @InjectExtra(MainActivity.PARAM_STRING) private String stringField;
    @InjectExtra(MainActivity.PARAM_STRING_ARRAY) private String[] stringFields;
    @InjectExtra(MainActivity.PARAM_STRING_ARRAY_LIST) private ArrayList<String> stringFieldList;
    @InjectExtra(MainActivity.PARAM_CHAR_SEQUENCE) private CharSequence charSequenceField;
    @InjectExtra(MainActivity.PARAM_CHAR_SEQUENCE_ARRAY) private CharSequence[] charSequenceFields;
	@InjectExtraJson(MainActivity.PARAM_STRING_JSON) private MyBean bean;
    
    @Inject private AccessibilityManager accessibilityManager;
    @Inject private AccountManager accountManager;
    @Inject private ActivityManager activityManager;
    @Inject private AlarmManager alarmManager;
    @Inject private AudioManager audioManager;
    @Inject private ConnectivityManager connectivityManager;
    @Inject private DevicePolicyManager devicePolicyManager;
    @Inject private DropBoxManager dropBoxManager;
    @Inject private InputMethodManager inputMethodManager;
    @Inject private KeyguardManager keyguardManager;
    @Inject private LayoutInflater layoutInflater;
    @Inject private LocationManager locationManager;
    @Inject private NotificationManager notificationManager;
    @Inject private PowerManager powerManager;
    @Inject private SearchManager searchManager;
    @Inject private SensorManager sensorManager;
    @Inject private TelephonyManager telephonyManager;
    @Inject private UiModeManager uiModeManager;
    @Inject private Vibrator vibrator;
    @Inject private WallpaperManager wallpaperManager;
    @Inject private WifiManager wifiManager;
    @Inject private WindowManager windowManager;
    
    @InjectPreference(MainActivity.KEY_BOOLEAN) private boolean booleanPreference;
    @InjectPreference(MainActivity.KEY_FLOAT) private float floatPreference;
    @InjectPreference(MainActivity.KEY_INT) private int intPreference;
    @InjectPreference(MainActivity.KEY_LONG) private long longPreference;
    @InjectPreference(MainActivity.KEY_STRING) private String stringPreference;
    @InjectPreference(MainActivity.KEY_STRING_SET) private Set<String> stringSetPreference;
	@InjectPreferenceJson(MainActivity.KEY_JSON) private MyBean bean2;
    
    @InjectResource(R.integer.integer1) private int integer1;
    @InjectResource(R.string.string1) private String string1;
    @InjectResource(R.array.integer_array1) private int[] integers1;
    @InjectResource(R.array.string_array1) private String[] strings1;
    @InjectResource(R.drawable.ic_launcher) private Drawable launcherDrawable;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		StringBuffer extraStringBuffer = new StringBuffer("Extra注入结果：");
		extraStringBuffer.append("\n").append("byteField").append("=").append(byteField);
		extraStringBuffer.append("\n").append("byteField").append("=").append(byteField);
		extraStringBuffer.append("\n").append("shortField").append("=").append(shortField);
		extraStringBuffer.append("\n").append("intField").append("=").append(intField);
		extraStringBuffer.append("\n").append("longField").append("=").append(longField);
		extraStringBuffer.append("\n").append("charField").append("=").append(charField);
		extraStringBuffer.append("\n").append("floatField").append("=").append(floatField);
		extraStringBuffer.append("\n").append("doubleField").append("=").append(doubleField);
		extraStringBuffer.append("\n").append("booleanField").append("=").append(booleanField);
		extraStringBuffer.append("\n").append("stringField").append("=").append(stringField);
		extraStringBuffer.append("\n").append("charSequenceField").append("=").append(charSequenceField);
		extraStringBuffer.append("\n").append("byteFields").append("=").append(Arrays.toString(byteFields));
		extraStringBuffer.append("\n").append("shortFields").append("=").append(Arrays.toString(shortFields));
		extraStringBuffer.append("\n").append("intFields").append("=").append(Arrays.toString(intFields));
		extraStringBuffer.append("\n").append("longFields").append("=").append(Arrays.toString(longFields));
		extraStringBuffer.append("\n").append("charFields").append("=").append(Arrays.toString(charFields));
		extraStringBuffer.append("\n").append("floatFields").append("=").append(Arrays.toString(floatFields));
		extraStringBuffer.append("\n").append("doubleFields").append("=").append(Arrays.toString(doubleFields));
		extraStringBuffer.append("\n").append("booleanFields").append("=").append(Arrays.toString(booleanFields));
		extraStringBuffer.append("\n").append("stringFields").append("=").append(Arrays.toString(stringFields));
		extraStringBuffer.append("\n").append("stringFieldList").append("=").append(stringFieldList.toString());
		extraStringBuffer.append("\n").append("charSequenceFields").append("=").append(Arrays.toString(charSequenceFields));
		extraStringBuffer.append("\n\n").append(bean.getName()).append(" ").append(bean.getSex()).append(" ").append(bean.getEmail());
		textView2.setText(extraStringBuffer.toString());
		
		boolean success = true; 
		try {
			for(Field field : ReflectUtils.getFields(getClass(), true, false, false, false)){
				if(field.isAnnotationPresent(Inject.class)){
					field.setAccessible(true);
					if(field.get(this) == null){
						success = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		textView3.setText("系统服务注入"+(success?"成功":"失败"));
		
		StringBuffer preferenceStringBuffer = new StringBuffer("Preference注入结果：");
		preferenceStringBuffer.append("\n").append("booleanPreference").append("=").append(booleanPreference);
		preferenceStringBuffer.append("\n").append("floatPreference").append("=").append(floatPreference);
		preferenceStringBuffer.append("\n").append("intPreference").append("=").append(intPreference);
		preferenceStringBuffer.append("\n").append("longPreference").append("=").append(longPreference);
		preferenceStringBuffer.append("\n").append("stringPreference").append("=").append(stringPreference);
		preferenceStringBuffer.append("\n").append("stringSetPreference").append("=").append(stringSetPreference);
		preferenceStringBuffer.append("\n\n").append(bean2.getName()).append(" ").append(bean2.getSex()).append(" ").append(bean2.getEmail());
		textView4.setText(preferenceStringBuffer.toString());
		
		StringBuffer resourceStringBuffer = new StringBuffer("Resource注入结果：");
		resourceStringBuffer.append("\n").append("integer1").append("=").append(integer1);
		resourceStringBuffer.append("\n").append("string1").append("=").append(string1);
		resourceStringBuffer.append("\n").append("integers1").append("=").append(Arrays.toString(integers1));
		resourceStringBuffer.append("\n").append("strings1").append("=").append(Arrays.toString(strings1));
		textView5.setText(resourceStringBuffer);
		textView5.setCompoundDrawablePadding(16);
		textView5.setCompoundDrawablesWithIntrinsicBounds(launcherDrawable, null, null, null);
	}
```

##Downloads
[android-happy-inject-1.0.0.jar](https://github.com/xiaopansky/Android-HappyInject/raw/master/releases/android-happy-inject-1.0.0.jar)

[android-happy-inject-1.0.0-with-src.jar](https://github.com/xiaopansky/Android-HappyInject/raw/master/releases/android-happy-inject-1.0.0-with-src.jar)

##License
```java
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
```