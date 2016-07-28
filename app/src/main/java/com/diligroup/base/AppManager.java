package com.diligroup.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 * @author
 * @createdate
 * @description App管理器，用于管理所有的activity 使用时注意堆栈后进先出原则
 */
public class AppManager {

	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后压入的），可能为null
	 */
	public Activity currentActivity() {
		Activity activity = null;
		if (activityStack != null && activityStack.size() > 0) {
			try {
				activity = activityStack.lastElement();
			} catch (NoSuchElementException e) {
				activity = null;
			}
		}
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后压入的）
	 */
	public void finishActivity() {
		Activity activity = null;
		if (activityStack != null && activityStack.size() > 0) {
			try {
				activity = activityStack.lastElement();
			} catch (NoSuchElementException e) {
				activity = null;
			}
		}
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		Activity removeActivity = null;
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				removeActivity = activity;
				break;
			}
		}
		if (removeActivity != null) {
			finishActivity(removeActivity);
		}
	}

	/**
	 * 得到指定的Activity
	 * 
	 * @param cls
	 */
	public Activity getActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				return activity;
			}
		}
		return null;
	}

	/**
	 * 结束所有的
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出应用程序
	 */
	@SuppressWarnings("deprecation")
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
		}
	}
}