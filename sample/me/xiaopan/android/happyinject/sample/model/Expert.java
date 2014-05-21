package me.xiaopan.android.happyinject.sample.model;

/**
 * 专家
 */
public class Expert {
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 专业
	 */
	private String major;
	
	public Expert(String name, String major) {
		this.name = name;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public String getMajor() {
		return major;
	}
}