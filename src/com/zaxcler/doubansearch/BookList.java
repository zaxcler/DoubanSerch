package com.zaxcler.doubansearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import com.zaxcler.infos.BookInfo;
import com.zaxcler.tools.AsyncDownload;
import com.zaxcler.tools.JsonTools;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
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

public class BookList extends Fragment {
	private ListView booklistview;
	private EditText bookname;
	private TextView search;

	public BookList() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.book_listview, null);
		booklistview = (ListView) view.findViewById(R.id.book_listview);
		bookname = (EditText) view.findViewById(R.id.book_edittext);
		search = (TextView) view.findViewById(R.id.book_search);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new AsyncSearch().execute();
			}
		});
		booklistview.setOnItemClickListener(new itemClickListener());
		return view;
	}

	private class itemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			BookInfo bookInfo=(BookInfo) parent.getAdapter().getItem(position);
			Intent intent=new Intent(getActivity(),BookDetails.class);
			Bundle bundle=new Bundle();
			bundle.putSerializable("bookinfo", bookInfo);
			intent.putExtras(bundle);
			startActivity(intent);
			
		}
		
	}
	private class AsyncSearch extends AsyncTask<String, integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String jsonString = "";
			System.out.println(bookname.getText().toString());
			String sb;
			try {
				/*
				 * 改变中文部分的编码格式
				 */
				sb = URLEncoder.encode("" + bookname.getText(), "utf-8");
				String encodeSting = "https://api.douban.com/v2/book/search?q="
						+ sb;
				URL url = new URL(encodeSting);

				HttpsURLConnection connection = (HttpsURLConnection) url
						.openConnection();
				connection.setConnectTimeout(5000);
				connection.setDoInput(true);
				connection.setRequestMethod("GET");
				if (connection.getResponseCode() == 200) {
					InputStream inputStream = connection.getInputStream();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(inputStream));
					StringBuffer buffer = new StringBuffer();
					String line = "";
					while ((line = reader.readLine()) != null) {
						buffer.append(line);

					}
					jsonString = buffer.toString();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return jsonString;
		}

		@Override
		protected void onPostExecute(String result) {
			List<BookInfo> list = JsonTools.getBookInfoList(result);
			if (list.isEmpty()) {
				Toast.makeText(getActivity(), "没有搜索到到结果", 0).show();
			}
			BookListAdpter adapter = new BookListAdpter(list);
			booklistview.setAdapter(adapter);

		}

	}

	/**
	 * listview 的适配器
	 * 
	 * @author zaxcler
	 * 
	 */
	private class BookListAdpter extends BaseAdapter {
		private List<BookInfo> list;
		private LayoutInflater inflater;
		private ImageView book_photo;
		private TextView book_name;
		private TextView book_auther;
		private TextView book_score;
		private TextView book_decribe;

		public BookListAdpter(List<BookInfo> list) {
			inflater = inflater.from(getActivity());
			this.list = list;
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
			if (convertView==null) {
				convertView= inflater.inflate(R.layout.bookiteam, null);
			}

			book_photo = (ImageView) convertView.findViewById(R.id.book_photo);
			book_name = (TextView) convertView.findViewById(R.id.book_name);
			book_auther = (TextView) convertView.findViewById(R.id.book_auther);
			book_score = (TextView) convertView.findViewById(R.id.book_score);
			book_decribe = (TextView) convertView.findViewById(R.id.book_describe);
			Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.loading);
			book_photo.setImageBitmap(bm);
			new AsyncDownload(list.get(position).getPhoto_url(), book_photo)
					.execute();
			book_auther.setText("作者："+list.get(position).getAuther());
			book_name.setText(list.get(position).getName());
			book_decribe.setText(list.get(position).getDescribe());
			book_score.setText("评分："+list.get(position).getScore() + "/10");
			return convertView;
		}

	}

}
