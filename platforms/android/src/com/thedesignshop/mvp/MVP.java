/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.thedesignshop.mvp;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;

import org.apache.cordova.*;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


public class MVP extends CordovaActivity 
{
	JavaScriptInterface jsInterface;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        // Set by <content src="index.html" /> in config.xml
        super.loadUrl(Config.getStartUrl());
        //super.loadUrl("file:///android_asset/www/index.html");
    
        	jsInterface = new JavaScriptInterface(MVP.this);
		
		super.appView.addJavascriptInterface(jsInterface,"JSInterface");
    }
    
    public class JavaScriptInterface {
		public Activity mContext;

		public JavaScriptInterface(Activity c) {
			this.mContext = c;
		}

		@JavascriptInterface
		public void openApp(String packageName) {
			Intent i = getActivity().getApplicationContext().getPackageManager().getLaunchIntentForPackage(packageName);
			getActivity().getApplicationContext().startActivity(i);
		}
	}
}