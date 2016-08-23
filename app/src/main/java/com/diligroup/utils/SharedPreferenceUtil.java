package com.diligroup.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.diligroup.base.DiliApplication;


/**
 * SharedPreference工具类
 * @author cwj
 */
public class SharedPreferenceUtil {
	private SharedPreferences sp;
	Editor editor;
	
	@SuppressLint("CommitPrefEdits")
	public SharedPreferenceUtil() {
		sp = DiliApplication.getContext().getSharedPreferences("auto_login", Context.MODE_PRIVATE);
		editor = sp.edit();
	}
	@SuppressLint("CommitPrefEdits")
	public SharedPreferenceUtil(String name) {
		sp = DiliApplication.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
		editor = sp.edit();
	}
	public boolean getBoolean(String key, boolean value) {
		return sp.getBoolean(key, value);
	}

	public int getInt(String key, int value) {
		return sp.getInt(key, value);
	}

	public long getLong(String key, long value) {
		return sp.getLong(key, value);
	}

	public String getString(String key, String value) {
		return sp.getString(key, value);
	}

	public float getFloat(String key, float value)
	{
		return sp.getFloat(key, value);
	}

	public void putBoolean(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void putInt(String key, int value) {
		editor.putInt(key, value);
		editor.commit();
	}

	public void putLong(String key, long value) {
		editor.putLong(key, value);
		editor.commit();
	}

	public void putString(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	public void putFloat(String key, float value) {
		editor.putFloat(key, value);
		editor.commit();
	}

	public void remove(String key){
		editor.remove(key);
		editor.commit();
	}

	public void clear(){
		editor.clear();
		editor.commit();
	}

}
