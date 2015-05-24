package com.zaxcler.doubansearch;

import com.zaxcler.infos.MusicInfo;
import com.zaxcler.tools.AsyncDownload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicDetails extends Activity{
	private ImageView music_photo;
	private TextView music_name;
	private TextView music_author;
	private TextView music_score;
	private TextView music_time;
	private TextView music_describe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_musicdetails);
		init();
	}
	private void init(){
		music_photo=(ImageView) findViewById(R.id.music_photo);
		music_name=(TextView) findViewById(R.id.music_name);
		music_score=(TextView) findViewById(R.id.music_score);
		music_time=(TextView) findViewById(R.id.music_time);
		music_describe=(TextView) findViewById(R.id.music_describe);
		music_author=(TextView) findViewById(R.id.music_author);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		MusicInfo musicInfo=(MusicInfo) bundle.getSerializable("musicdetails");
		new AsyncDownload(musicInfo.getMusic_photo(), music_photo).execute();
		music_name.setText(musicInfo.getMusic_name());
		music_score.setText(musicInfo.getMusic_score());
		music_time.setText(musicInfo.getMusic_genres());
		music_author.setText(musicInfo.getMusic_author());
		
	}
}
