package com.zaxcler.tools;

import android.graphics.Bitmap;

/**
 * 缓存类
 */
import android.support.v4.util.LruCache;

public class MeroryCacheTools {
	private LruCache<String , Bitmap> mMemoryCache;
	
	public MeroryCacheTools(int maxSize) {
		 mMemoryCache = new LruCache<String, Bitmap>(maxSize) {
		        @Override
		        protected int sizeOf(String key, Bitmap bitmap) {
		            // The cache size will be measured in kilobytes rather than
		            // number of items.
		            return bitmap.getByteCount() / 1024;
		        }
		 };
	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
	    if (getBitmapFromMemCache(key) == null) {
	        mMemoryCache.put(key, bitmap);
	    }
	}
	public Bitmap getBitmapFromMemCache(String key) {
	    return mMemoryCache.get(key);
	}
}
