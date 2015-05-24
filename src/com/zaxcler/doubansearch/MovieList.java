package com.zaxcler.doubansearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.zaxcler.doubansearch.R.drawable;
import com.zaxcler.infos.MovieInfo;
import com.zaxcler.tools.AsyncDownload;
import com.zaxcler.tools.JsonTools;

import android.R.integer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieList extends Fragment {
	private LinearLayout left;
	private LinearLayout right;
	private EditText moviename;
	private TextView search;

	public MovieList() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.movie_listview, null);
		left = (LinearLayout) view.findViewById(R.id.left);
		right = (LinearLayout) view.findViewById(R.id.right);
		moviename=(EditText) view.findViewById(R.id.movie_edittext);
		search = (TextView) view.findViewById(R.id.movie_search);
		search.setOnClickListener(new clickListner());
		return view;
	}

	private class clickListner implements OnClickListener {

		@Override
		public void onClick(View v) {
			new AsyncInit().execute();

		}

	}

	private void init(String jsonData) {
		final List<MovieInfo> list = JsonTools.getMovieList(jsonData);
		if (list.isEmpty()) {
			Toast.makeText(getActivity(), "很遗憾，没有搜索到结果", 1).show();
		}
		left.removeAllViews();
		right.removeAllViews();
		for (int i = 0; i < list.size(); i++) {
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View view = inflater.inflate(R.layout.movieiteam, null);
			ImageView moviephoto = (ImageView) view
					.findViewById(R.id.movie_photo);
			TextView moviename = (TextView) view.findViewById(R.id.movie_name);
			TextView moviescore = (TextView) view
					.findViewById(R.id.movie_score);
			TextView moviecollectcount = (TextView) view
					.findViewById(R.id.movie_collect_count);					
			Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.loading);
			moviephoto.setImageBitmap(bm);
			new AsyncDownload(list.get(i).getPhoto_url(), moviephoto).execute();
			moviename.setText(list.get(i).getName());
			moviescore.setText("评分：" + list.get(i).getSocre() + "/10");
			moviecollectcount.setText(list.get(i).getCollect_count() + "人评价");
			final int position=i;
			view.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(getActivity(),MovieDetails.class);
					Bundle bundle=new Bundle();
					bundle.putSerializable("moviedetails", list.get(position));
					intent.putExtras(bundle);
					startActivity(intent);
					
				}
			});
			left.measure(0, 0);
			right.measure(0, 0);
			boolean flage = left.getMeasuredHeight() <= right
					.getMeasuredHeight() ? true : false;
			if (flage) {
				left.addView(view);
			} else {
				right.addView(view);
			}
		}

	}

	private class AsyncInit extends AsyncTask<String, integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String jsonString = "";
			System.out.println(moviename.getText().toString());
			String sb;
			try {
				/*
				 * 改变中文部分的编码格式
				 */
				sb = URLEncoder.encode("" + moviename.getText(), "utf-8");
				String encodeSting = "https://api.douban.com/v2/movie/search?q="
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
			init(result);
		}

	}
}
