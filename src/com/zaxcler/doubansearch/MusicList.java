package com.zaxcler.doubansearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.zaxcler.infos.MusicInfo;
import com.zaxcler.tools.AsyncDownload;
import com.zaxcler.tools.JsonTools;
import com.zaxcler.tools.MeroryCacheTools;

import android.R.integer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MusicList extends Fragment{

	private ListView music_list;
	private EditText music_name;
	private TextView search;
	
	public MusicList() { 
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.music_listview,null);
		music_list=(ListView) view.findViewById(R.id.music_listview);
		music_name=(EditText) view.findViewById(R.id.music_edittext);
		search=(TextView) view.findViewById(R.id.music_search);
		music_list.setOnItemClickListener(new itemClickListener());
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AsyncInit().execute();				
			}
		});
		return view;
	}

	private class itemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			MusicInfo musicInfo=(MusicInfo) parent.getAdapter().getItem(position);
			Intent intent=new Intent(getActivity(),MusicDetails.class);
			Bundle bundle=new Bundle();
			bundle.putSerializable("musicdetails", musicInfo);
			intent.putExtras(bundle);
			startActivity(intent);
		}
		
	}
	private class AsyncInit extends AsyncTask<String, integer, String>{

		@Override
		protected String doInBackground(String... params) {
			String jsondata="";
			try {
				/*
				 * 改变中文的编码格式
				 */
				String sb = URLEncoder.encode(""+music_name.getText(), "utf-8");
				String path="https://api.douban.com/v2/music/search?q="+sb;
				URL url=new URL(path);
				HttpURLConnection connection=(HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.setConnectTimeout(5000);
				connection.setRequestMethod("GET");
				if(connection.getResponseCode()==200){
					InputStream inputStream=connection.getInputStream();
					BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
					StringBuffer buffer=new StringBuffer();
					String line="";
					while ((line=reader.readLine())!=null) {
						buffer.append(line);
					}
					jsondata=buffer.toString();
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return jsondata;
		}
		@Override
		protected void onPostExecute(String result) {
			List<MusicInfo> list=JsonTools.getMusicList(result);
			if (list.isEmpty()) {
				Toast.makeText(getActivity(), "没有搜索到到结果", 0).show();
			}
			listAdapter adapter=new listAdapter(list);
			music_list.setAdapter(adapter);
			
		}
		
	}
	
	private class listAdapter extends BaseAdapter{
		private MeroryCacheTools meroryCacheTools;
		private List<MusicInfo> list;
		private LayoutInflater inflater;
		public listAdapter(List<MusicInfo> list){
			this.list=list;
			inflater=LayoutInflater.from(getActivity());
			meroryCacheTools=new MeroryCacheTools((int)(Runtime.getRuntime().maxMemory()/1024));
		}
		
		@Override
		public int getCount() {

			return list.size();
		}

		@Override
		public Object getItem(int position) {

			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view=inflater.inflate(R.layout.musiciteam, null);
			ImageView music_photo=(ImageView) view.findViewById(R.id.music_photo);
			TextView music_name=(TextView) view.findViewById(R.id.music_name);
			TextView music_author=(TextView) view.findViewById(R.id.music_auther);
			TextView music_score=(TextView) view.findViewById(R.id.music_score);
			Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.loading);
			music_photo.setImageBitmap(bm);
			new AsyncDownload(list.get(position).getMusic_photo(), music_photo).execute();
			music_name.setText(list.get(position).getMusic_name());
			music_author.setText("作者："+list.get(position).getMusic_author());
			music_score.setText("评分："+list.get(position).getMusic_score()+"/10");
			return view;
		}
		
	}
}
