# ![Logo](https://github.com/xiaopansky/HappyInject/raw/master/res/drawable-mdpi/ic_launcher.png) HappyInject

HappyInject是Android上的一个注入类库，类似于RoboGuice，但比RoboGuice更轻量级。

## Features
>* 提供Layout、View、Resource、Bundle Extra、Service、SharedPreferences、Fragment等资源的注入；
>* 特别提供了InjectAdapter、InjectExpandableListAdapter通过注入来避免创建新的Adapter；
>* 由于只专注于上述资源的注入，因此比RoboGuice更轻量级。

## Usage
可继承的超类分以下四种：

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

Adapter：
>* InjectAdapter
>* InjectExpandableListAdapter

#### 可使用的注解有以下几种：
>* DisableInjector：禁用注入功能。因为注解功能默认是开启的，如果你不想使用注入的话就可是使用此注解来禁用注入功能；
>* Inject：注入系统服务（通过getService()的各种Manager）或者ShardPreferences；在注入ShardPreferences时你可以通过sharedPreferencesName参数指定名称，不指定时将注入默认的ShardPreferences；适用于以下场合：
    1. Activity
    2. Fragment
    3. Service
    4. BroadcastRecevier
    5. ContextProvidet
    6. Loader
    7. InjectAdapter.ViewHolder
    8. InjectExpandableListAdapter.GroupViewHolder
    9. InjectExpandableListAdapter.ChildViewHolder
>* InjectContentView：注入内容视图。适用于以下场合：
    1. Activity
    2. Fragment
    3. InjectAdapter.ViewHolder
    4. InjectExpandableListAdapter.GroupViewHolder
    5. InjectExpandableListAdapter.ChildViewHolder
>* InjectExtra：注入Bundle中的参数。适用于以下场合：
    1. Activity：Bundle来自getIntent().getExtras()
    2. Fragment：Bundle来自getArguments()
    3. BroadcastReceiver：Bundle来自onReceive()方法中intent参数的getExtras()
>* InjectExtraJson：将Bundle中的字符串通过Gson转换成对象。适用于以下场合：
    1. Activity：Bundle来自getIntent().getExtras()
    2. Fragment：Bundle来自getArguments()
    3. BroadcastReceiver：Bundle来自onReceive()方法中intent参数的getExtras()
>* InjectFragment：注入Fragment。只支持FragmentActivity中Fragment类型的字段；
>* InjectParentMember：注入父类的成员变量。默认是不注入父类的成员变量的；适用于以下场合：
    1. Activity
    2. Fragment
    3. Service
    4. BroadcastRecevier
    5. ContextProvidet
    6. Loader
    7. InjectAdapter.ViewHolder
    8. InjectExpandableListAdapter.GroupViewHolder
    9. InjectExpandableListAdapter.ChildViewHolder
>* InjectPreference：注入SharedPreferences中的参数。SharedPreferencees的名称可通过sharedPreferencesName参数指定，不指定时将从默认的SharedPreferencees中获取参数；适用于以下场合：
    1. Activity
    2. Fragment
    3. Service
    4. BroadcastRecevier
    5. ContextProvidet
    6. Loader
    7. InjectAdapter.ViewHolder
    8. InjectExpandableListAdapter.GroupViewHolder
    9. InjectExpandableListAdapter.ChildViewHolder
>* InjectPreferenceJson：将SharedPreferences中的字符串通过Gson转换成对象适用于以下场合：
    1. Activity
    2. Fragment
    3. Service
    4. BroadcastRecevier
    5. ContextProvidet
    6. Loader
    7. InjectAdapter.ViewHolder
    8. InjectExpandableListAdapter.GroupViewHolder
    9. InjectExpandableListAdapter.ChildViewHolder
>* InjectResource：注入资源。支持的资源类型如下：
    1. boolean
    2. String
    3. String[]
    4. Integer
    5. Integer[]
    6. Drawable
    7. ColorStateList
    8. Animation
    9. Movie
    
    适用于以下场合：
    1. Activity
    2. Fragment
    3. Service
    4. BroadcastRecevier
    5. ContextProvidet
    6. Loader
    7. InjectAdapter.ViewHolder
    8. InjectExpandableListAdapter.GroupViewHolder
    9. InjectExpandableListAdapter.ChildViewHolder
>* InjectView：注入View。适用于以下场合：
    1. Activity
    2. Fragment
    3. InjectAdapter.ViewHolder
    3. InjectExpandableListAdapter.GroupViewHolder
    3. InjectExpandableListAdapter.ChildViewHolder
    
#### Example
##### InjectActivity：
假设我们要从MainActivity跳转到另外一个Activity，还要携带一些数据。
首先来看MainActivity的实现
```java
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView listView = new ListView(getBaseContext());
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
		String[] items = new String[]{"注入功能测试", "非注入功能测试", "FragmentDialog测试"};
		listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, android.R.id.text1, items));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
						Intent intent = new Intent(getBaseContext(), InjectTestActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					case 1 : 
						Intent intent2 = new Intent(getBaseContext(), NormalActivity.class);
						intent2.putExtras(bundle);
						startActivity(intent2);
						break;
					case 2 : 
						new TestDialogFragment().show(getSupportFragmentManager(), ""); 
						break;
				}
			}
		});
	}
}
```

其次来看一下普通的实现方式是怎么接收数据的

```java
public class NormalActivity extends Activity {
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private TextView textView5;

	private byte byteField;
	private byte[] byteFields;
	private short shortField;
	private short[] shortFields;
	private int intField;
	private int[] intFields;
	private long longField;
	private long[] longFields;
	private char charField;
	private char[] charFields;
	private float floatField;
	private float[] floatFields;
	private double doubleField;
	private double[] doubleFields;
	private boolean booleanField;
	private boolean[] booleanFields;
	private String stringField;
	private String[] stringFields;
	private ArrayList<String> stringFieldList;
	private CharSequence charSequenceField;
	private CharSequence[] charSequenceFields;
	private MyBean bean;
	
	private AccessibilityManager accessibilityManager;
	private AccountManager accountManager;
	private ActivityManager activityManager;
	private AlarmManager alarmManager;
	private AudioManager audioManager;
	private ConnectivityManager connectivityManager;
	private DevicePolicyManager devicePolicyManager;
	private DropBoxManager dropBoxManager;
	private InputMethodManager inputMethodManager;
	private KeyguardManager keyguardManager;
	private LayoutInflater layoutInflater;
	private LocationManager locationManager;
	private NotificationManager notificationManager;
	private PowerManager powerManager;
	private SearchManager searchManager;
	private SensorManager sensorManager;
	private TelephonyManager telephonyManager;
	private UiModeManager uiModeManager;
	private Vibrator vibrator;
	private WallpaperManager wallpaperManager;
	private WifiManager wifiManager;
	private WindowManager windowManager;
	
	private boolean booleanPreference;
	private float floatPreference;
	private int intPreference;
	private long longPreference;
	private String stringPreference;
	private Set<String> stringSetPreference;
	private MyBean bean2;
	
	private int integer1;
	private String string1;
	private int[] integers1;
	private String[] strings1;
	private Drawable launcherDrawable;
	
	@SuppressLint("ServiceCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView1 = (TextView) findViewById(R.id.text_main1);
		textView2 = (TextView) findViewById(R.id.text_main2);
		textView3 = (TextView) findViewById(R.id.text_main3);
		textView4 = (TextView) findViewById(R.id.text_main4);
		textView5 = (TextView) findViewById(R.id.text_main5);
		
		byteField = getIntent().getByteExtra(MainActivity.PARAM_BYTE, (byte)0);
		byteFields = getIntent().getByteArrayExtra(MainActivity.PARAM_BYTE_ARRAY);
		shortField = getIntent().getShortExtra(MainActivity.PARAM_SHORT, (short)0);
		shortFields = getIntent().getShortArrayExtra(MainActivity.PARAM_SHORT_ARRAY);
		intField = getIntent().getIntExtra(MainActivity.PARAM_INT, 0);
		intFields= getIntent().getIntArrayExtra(MainActivity.PARAM_INT_ARRAY);
		longField = getIntent().getLongExtra(MainActivity.PARAM_LONG, 0);
		longFields = getIntent().getLongArrayExtra(MainActivity.PARAM_LONG_ARRAY);
		charField = getIntent().getCharExtra(MainActivity.PARAM_CHAR, '0');
		charFields = getIntent().getCharArrayExtra(MainActivity.PARAM_CHAR_ARRAY);
		floatField = getIntent().getFloatExtra(MainActivity.PARAM_FLOAT, 0);
		floatFields = getIntent().getFloatArrayExtra(MainActivity.PARAM_FLOAT_ARRAY);
		doubleField = getIntent().getDoubleExtra(MainActivity.PARAM_DOUBLE, 0);
		doubleFields = getIntent().getDoubleArrayExtra(MainActivity.PARAM_DOUBLE_ARRAY);
		booleanField = getIntent().getBooleanExtra(MainActivity.PARAM_BOOLEAN, false);
		booleanFields = getIntent().getBooleanArrayExtra(MainActivity.PARAM_BOOLEAN_ARRAY);
		stringField = getIntent().getStringExtra(MainActivity.PARAM_STRING);
		stringFields = getIntent().getStringArrayExtra(MainActivity.PARAM_STRING_ARRAY);
		stringFieldList = getIntent().getStringArrayListExtra(MainActivity.PARAM_STRING_ARRAY_LIST);
		charSequenceField = getIntent().getCharSequenceExtra(MainActivity.PARAM_CHAR_SEQUENCE);
		charSequenceFields = getIntent().getCharSequenceArrayExtra(MainActivity.PARAM_CHAR_SEQUENCE_ARRAY);
		
		bean = new Gson().fromJson(getIntent().getStringExtra(MainActivity.PARAM_STRING_JSON), MyBean.class);
		
		accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
		accountManager = (AccountManager) getSystemService(Context.ACCOUNT_SERVICE);
		activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		dropBoxManager = (DropBoxManager) getSystemService(Context.DROPBOX_SERVICE);
		inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		uiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		wallpaperManager = (WallpaperManager) getSystemService(Context.WALLPAPER_SERVICE);
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		
		booleanPreference = PreferenceUtils.getBoolean(getBaseContext(), MainActivity.KEY_BOOLEAN);
		floatPreference = PreferenceUtils.getFloat(getBaseContext(), MainActivity.KEY_FLOAT);
		intPreference = PreferenceUtils.getInt(getBaseContext(), MainActivity.KEY_INT);
		longPreference = PreferenceUtils.getLong(getBaseContext(), MainActivity.KEY_LONG);
		stringPreference = PreferenceUtils.getString(getBaseContext(), MainActivity.KEY_STRING);
		stringSetPreference = PreferenceUtils.getStringSet(getBaseContext(), MainActivity.KEY_STRING_SET);
		bean2 = PreferenceUtils.getObject(getBaseContext(), MainActivity.KEY_JSON, MyBean.class);
		
		integer1 = getResources().getInteger(R.integer.integer1);
		string1 = getResources().getString(R.string.string1);
		integers1 = getResources().getIntArray(R.array.integer_array1);
		strings1 = getResources().getStringArray(R.array.string_array1);
		launcherDrawable = getResources().getDrawable(R.drawable.ic_launcher);
		
		//数据处理...
	}
}
```

最后看一下怎么采用注入的方式来接收数据

```java
@InjectContentView(R.layout.activity_main)
public class InjectTestActivity extends InjectActivity {
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
		
		//数据处理...
	}
}
```

这么一对比发现注入方式太简洁了，简直无与伦比了。其它的InjectService、InjectBroadcastReceiver等的使用方式同Activity类似。

##### InjectFragment
同样下来看一下通常的Fragment的实现：
```java
public class InformationListFragment extends Fragment {
	public static final String PARAM_REQUIRED_STRING_TYPE = "PARAM_REQUIRED_STRING_MEETING_TYPE";

	private MyClickLoadListView listView;
    private String type = null;

    @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		if(getArguments() != null){
		    type = getArguments().getString(PARAM_REQUIRED_STRING_TYPE);
		}
	}

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_information_list, null);
        listView = (ListView) rootView.findViewById(R.id.listView_news);
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// 数据处理...
	}
}
```

然后来看一下InjectFragment的实现：
```java
@InjectContentView(R.layout.fragment_information_list)
public class InformationListFragment extends MyFragment {
	public static final String PARAM_REQUIRED_STRING_TYPE = "PARAM_REQUIRED_STRING_MEETING_TYPE";

	@InjectView(R.id.listView_news) private MyClickLoadListView listView;
    @InjectExtra(PARAM_REQUIRED_STRING_TYPE) private String type = null;

    @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// 数据处理...
	}
}
```

##### InjectAdapter
首先来看一下普通的Adapter实现方式：
```java
public class ExpertAdapter extends BaseAdapter {
	private Context context;
	private List<Expert> experts;
	private Expert expert;
	
	public ExpertAdapter(Context context, List<Expert> experts){
		this.context = context;
		this.experts = experts;;
	}
	
	@Override
	public int getCount() {
		return experts.size();
	}

	@Override
	public Object getItem(int position) {
		return experts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item_expert, null);
			viewHolder = new ViewHolder();
			viewHolder.headPortraitImage = (ImageView) convertView.findViewById(R.id.image_expertItem_headPortrait);
			viewHolder.nameText = (TextView) convertView.findViewById(R.id.text_expertItem_name);
			viewHolder.majorText = (TextView) convertView.findViewById(R.id.text_expertItem_major);
			viewHolder.askQuestion = (Button) convertView.findViewById(R.id.button_expertItem_askQuestion);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		expert = experts.get(position);
		
		ImageLoader.getInstance(context).display(expert.getHeadPortraitUrl(), viewHolder.headPortraitImage, OptionsType.DEFAULT);
		viewHolder.nameText.setText(expert.getName());
		viewHolder.majorText.setText(context.getString(R.string.fromKey_major)+expert.getMajor());
		
		return convertView;
	}

	private class ViewHolder{
		ImageView headPortraitImage;
		TextView nameText;
		TextView majorText;
		Button askQuestion;
	}
}
```
然后你是这样用的
```java
List<Expert> experts = ...;
listView.setAdapter(new ExpertAdapter(getBaseContext(), experts));
```

那么采用InjectAdapter就会方便很多，可以让你省去频繁创建Adapter的苦恼，并且思路更清晰。

首先你需要创建一个ViewHolder，实现InjectAdapter.ViewHolder接口，来影射你的R.layout.list_item_expert布局，如下：
```java
@InjectContentView(R.layout.list_item_expert)
public class ExpertViewHolder implements InjectAdapter.ViewHolder<Expert>{
    @InjectView(R.id.image_expertItem_headPortrait) ImageView headPortraitImage;
    @InjectView(R.id.text_expertItem_name) TextView nameText;
    @InjectView(R.id.text_expertItem_major) TextView majorText;
    @InjectView(R.id.button_expertItem_askQuestion) Button askQuestion;

    @Override
    public void setValues(Context context, int position, Expert expert) {
        ImageLoader.getInstance(context).display(expert.getHeadPortraitUrl(), headPortraitImage, OptionsType.DEFAULT);
        nameText.setText(expert.getName());
        majorText.setText(expert.getMajor());
    }
}
```
然后你就可以直接使用InjectAdapter了，如下：
```java
List<Expert> experts = ...;
listView.setAdapter(new InjectAdapter<Expert, ExpertViewHolder>(getBaseContext(), ExpertViewHolder.class, experts));
```
如果你还需要为askQuestion按钮绑定点击事件，那么同样很简单，如下：
```java
List<Expert> experts = ...;
listView.setAdapter(new InjectAdapter<Expert, ExpertViewHolder>(getBaseContext(), ExpertViewHolder.class, experts, new InjectAdapter.BindingEventListener<ExpertViewHolder>() {
    @Override
    public void bindingEvent(ExpertViewHolder viewHolder) {
        viewHolder.askQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理点击事件...
            }
        });
    }
}));
```
InjectExpandableListAdapter的用法同InjectAdapter一样。

## Downloads
>* [android-happy-inject-1.2.0.jar](https://github.com/xiaopansky/HappyInject/raw/master/releases/android-happy-inject-1.2.0.jar)

>* [android-happy-inject-1.2.0-with-src.jar](https://github.com/xiaopansky/HappyInject/raw/master/releases/android-happy-inject-1.2.0-with-src.jar)

Dependencies
>* [android-support-v4.jar](https://github.com/xiaopansky/HappyInject/raw/master/libs/android-support-v4.jar) 可选的。如果你要使用InjectFragmentActivity、InjectFragment、InjectListFeagment、InjectDialogFragment就必须要引入此类库
>* [android-support-v7-appcompat.jar](https://github.com/xiaopansky/HappyInject/raw/master/libs/android-support-v7-appcompat.jar)
可选的。如果你要使用InjectActionBarActivity就必须要引入此类库，值的注意的是使用此类库的时候一定要使用sdk\extras\android\support\v7\appcompat\libs目录下的android-support-v4.jar（因为这个目录下的android-support-v4.jar包含有android-support-v7-appcompat.jar需要的类）
>* [gson-2.2.4.jar](https://github.com/xiaopansky/HappyInject/raw/master/libs/gson-2.2.4.jar) 可选的。如果你要使用InjectExtraJson、InjectPreferenceJson就必须要引入此类库
>*  如果你要使用InjectMapActivity就必须选择Google APIs

## Change Log
### 1.2.0
>* 调整Injector的Object设置逻辑，使之支持重复利用
>* 调整InjectAdapter和InjectExpandableListAdapter的注入实现方式，采用Injector来注入，可注入Resource、Service、Preferences等

### 1.1.1
>* InjectAdapter和InjectExpandableListAdapter的setValues()方法增加position参数

### 1.1.0
>* 全面优化注入逻辑，新逻辑为一次性将不同类型的注入全部分好类，到注入的时候，将不再有逻辑判断直接循环注入
>* 修复InjectService和InjectIntentService注入失败的BUG，原因是应该在onCreate()方法中注入，却写成了在构造函数中注入

## License
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