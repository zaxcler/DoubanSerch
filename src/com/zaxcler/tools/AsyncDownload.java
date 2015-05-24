package com.zaxcler.tools;

import android.R.integer;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class AsyncDownload extends AsyncTask<String, integer, Bitmap> {
	private ImageView imageView;
	private String url;
	private Bitmap bitmap;

	public AsyncDownload(String url,ImageView imageView) {
		this.imageView=imageView;
		this.url=url;
		
	}

		@Override
		protected Bitmap doInBackground(String... params) {
			bitmap =DownloadImageUtil.getBitmap(url);
			return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			imageView.setImageBitmap(result);
			super.onPostExecute(result);
	
		}
		public Bitmap getBitmap(){
			return bitmap;
			
		}

}
