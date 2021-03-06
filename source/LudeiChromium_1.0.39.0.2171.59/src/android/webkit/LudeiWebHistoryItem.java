/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.webkit;

import org.chromium.content_public.browser.NavigationEntry;

import com.jsaiyan.Unsafe;

import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.WebHistoryItem;
/**
 * WebView Chromium implementation of WebHistoryItem. Simple immutable wrapper
 * around NavigationEntry
 */
public class LudeiWebHistoryItem extends WebHistoryItem {
    private String mUrl;
    private String mOriginalUrl;
    private String mTitle;
    private Bitmap mFavicon;
    
    /* package */ LudeiWebHistoryItem(NavigationEntry entry) {
        mUrl = entry.getUrl();
        mOriginalUrl = entry.getOriginalUrl();
        mTitle = entry.getTitle();
        mFavicon = entry.getFavicon();
    }
    
    public static LudeiWebHistoryItem newInstance(NavigationEntry entry) {
    	LudeiWebHistoryItem instance = null;
    	if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
    		Unsafe unsafe = Unsafe.instance();
        	instance = (LudeiWebHistoryItem) unsafe.allocateObject(LudeiWebHistoryItem.class);
        	instance.init(entry);
        	
    	} else {
    		instance = new LudeiWebHistoryItem(entry);
    	}
    	
		return instance;
    }
    
    private void init(NavigationEntry entry) {
    	mUrl = entry.getUrl();
        mOriginalUrl = entry.getOriginalUrl();
        mTitle = entry.getTitle();
        mFavicon = entry.getFavicon();
    }
    
    /**
     * See {@link android.webkit.WebHistoryItem#getUrl}.
     */
    @Override
    public String getUrl() {
        return mUrl;
    }
    /**
     * See {@link android.webkit.WebHistoryItem#getOriginalUrl}.
     */
    @Override
    public String getOriginalUrl() {
        return mOriginalUrl;
    }
    /**
     * See {@link android.webkit.WebHistoryItem#getTitle}.
     */
    @Override
    public String getTitle() {
        return mTitle;
    }
    /**
     * See {@link android.webkit.WebHistoryItem#getFavicon}.
     */
    @Override
    public Bitmap getFavicon() {
        return mFavicon;
    }
    // Clone constructor.
    private LudeiWebHistoryItem(
            String url, String originalUrl, String title, Bitmap favicon) {
        mUrl = url;
        mOriginalUrl = originalUrl;
        mTitle = title;
        mFavicon = favicon;
    }
    /**
     * See {@link android.webkit.WebHistoryItem#clone}.
     */
    @Override
    public synchronized LudeiWebHistoryItem clone() {
        return new LudeiWebHistoryItem(mUrl, mOriginalUrl, mTitle, mFavicon);
    }
}