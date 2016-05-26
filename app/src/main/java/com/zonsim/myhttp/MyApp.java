package com.zonsim.myhttp;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * application 初始化Logger
 * Created by tang-jw on 2016/5/26.
 */
public class MyApp extends Application {
	
	public static MyApp application;
	
	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		// https://github.com/orhanobut/logger
		Logger.init().setLogLevel(LogLevel.FULL);
	}
}
