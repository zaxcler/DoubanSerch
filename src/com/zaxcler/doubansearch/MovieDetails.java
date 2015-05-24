package com.zaxcler.doubansearch;

import java.util.List;

import com.zaxcler.infos.Avatars;
import com.zaxcler.infos.MovieInfo;
import com.zaxcler.tools.AsyncDownload;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MovieDetails extends Activity {
	private ImageView movie_photo;
	private TextView movie_name;
	private TextView movie_directors;
	private TextView movie_score;
	private TextView movie_time;
	private LinearLayout actor_layout;
	private TextView movie_descirbe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_moviedetails);
		init();

	}

	private void init(){
		movie_photo=(ImageView) findViewById(R.id.movie_photo);
		movie_name=(TextView) findViewById(R.id.movie_name);
		movie_directors=(TextView) findViewById(R.id.movie_directors);
		movie_score=(TextView) findViewById(R.id.movie_score);
		movie_time=(TextView) findViewById(R.id.movie_pubdate);
		actor_layout=(LinearLayout)findViewById(R.id.actor_info);
		movie_descirbe=(TextView) findViewById(R.id.movie_describe);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		MovieInfo movieInfo=(MovieInfo) bundle.get("moviedetails");
		new AsyncDownload(movieInfo.getPhoto_url(), movie_photo).execute();
		movie_name.setText(movieInfo.getName());
		String directors="";
		
		if (movieInfo.getDirectors()!=null) {
			List<Avatars> avatars=(List<Avatars>) movieInfo.getDirectors();
			for (int i = 0; i <avatars.size(); i++) {
				if (avatars.get(i).getName()!=null) {
					directors+=avatars.get(i).getName()+" ";
				}
			}
		}
		
		movie_directors.setText(" 导演："+directors);
		movie_score.setText("评分："+movieInfo.getSocre()+"/10");
		movie_time.setText("时间："+movieInfo.getTime());
		movie_descirbe.setText(movieInfo.getDescribe());
		List<Avatars> actors=(List<Avatars>) movieInfo.getActors();
		LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
		for (int i = 0; i < actors.size(); i++) {
			View view=inflater.inflate(R.layout.actor_info, null);
			ImageView actorphoto=(ImageView) view.findViewById(R.id.actor_photo);
			TextView actorname=(TextView) view.findViewById(R.id.actor_name);
			actorname.setText(actors.get(i).getName());
			if(actors.get(i).getPhoto_url()!=null){
				new AsyncDownload(actors.get(i).getPhoto_url(), actorphoto).execute();
			}
			
			actor_layout.addView(view);
		}
		
	}
}
