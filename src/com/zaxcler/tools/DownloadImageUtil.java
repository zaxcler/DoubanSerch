package com.zaxcler.tools;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class DownloadImageUtil {
	/**
	 *根据path获取图片的 byte[]数据
	 * @param path
	 * @return
	 */
public static byte[] downloadImage(String path){
	HttpURLConnection connection=null;
	try {
		URL 	url=new URL(path);
		//打开链接
		connection=(HttpURLConnection)url.openConnection();
		//设置链接超时
		connection.setConnectTimeout(3000);
		//	设置打开输入流
		connection.setDoInput(true);
		//设置请求方法为"GET"
		connection.setRequestMethod("GET");
		//得到链接状态
		int responseCode=connection.getResponseCode();
		if(responseCode==200){
			//得到输入流
		InputStream inputStream=connection.getInputStream();
		byte[] buffer=new byte[2014];
		int len=0;
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		while((len=inputStream.read(buffer))!=-1){
			outputStream.write(buffer,0,len);
		}
		byte[] data=outputStream.toByteArray();
		inputStream.close();
		outputStream.close();
		return data;
		}
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}
public static Bitmap getBitmap(String path){
	byte[] data=downloadImage(path);
	
	Bitmap bitmap =BitmapFactory.decodeByteArray(data, 0, data.length); 

	return bitmap;
}

	public static Bitmap downLoadImageChangeToBitmap(String path){
		HttpURLConnection connection=null;
		Bitmap bitmap = null;
		try {
			URL 	url=new URL(path);
			//打开链接
			connection=(HttpURLConnection)url.openConnection();
			//设置链接超时时间
			connection.setConnectTimeout(3000);
			//	设置请求方法
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			//得到链接状态码
			int responseCode=connection.getResponseCode();
			if(responseCode==200){
				//得到输入流
			InputStream inputStream=connection.getInputStream();
			BufferedInputStream stream=new BufferedInputStream(inputStream);
			//标记输入流，为reset做准备 
			stream.mark(0);
			BitmapFactory.Options options=new BitmapFactory.Options();
			//设置injustdecodebounds为true 	BitmapFactory.decodeStream(stream, null, options);只会得到图片的大小不会为他分配空间
			options.inJustDecodeBounds=true;
			BitmapFactory.decodeStream(stream, null, options);
			//判断图片大小，进行缩放 缩放到1/4
			int photoSize=(options.outHeight*options.outWidth);
			if(photoSize>1024*1024*4){
				options.inSampleSize=2;
			}
			else {
				//不进行缩放
				options.inSampleSize=1;
			}
			stream.mark(0);
			options.inJustDecodeBounds=false;
			//把输入流头部移到mark处
			stream.reset();
			bitmap=BitmapFactory.decodeStream(stream, null, options);
			stream.close();
			inputStream.close();
			}
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
		
	}

}
