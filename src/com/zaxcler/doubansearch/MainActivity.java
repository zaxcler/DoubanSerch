package com.zaxcler.doubansearch;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private TextView book_button;
	private TextView movie_button;
	private TextView music_button;
	private View book_view;
	private View movie_view;
	private View music_view;
	private FragmentManager manager;
	private BookList bookList;
	private MovieList movieList;
	private MusicList musicList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		findview();
		book_view.setOnClickListener(new clickListener());
		movie_view.setOnClickListener(new clickListener());
		music_view.setOnClickListener(new clickListener());
		manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		bookList = new BookList();
		transaction.add(R.id.content, bookList);
		book_button.setTextColor(getResources().getColor(R.color.blue));
		transaction.commit();

	}

	private void findview() {
		book_button = (TextView) findViewById(R.id.book_button);
		movie_button = (TextView) findViewById(R.id.movie_button);
		music_button = (TextView) findViewById(R.id.music_button);
		book_view = (View) findViewById(R.id.book);
		movie_view = (View) findViewById(R.id.movie);
		music_view = (View) findViewById(R.id.music);
	}

	private void hideAllFragment() {
		FragmentTransaction transaction = manager.beginTransaction();

		if (bookList != null) {
			transaction.hide(bookList);
		}
		if (movieList != null) {
			transaction.hide(movieList);
		}
		if (musicList != null) {
			transaction.hide(musicList);
		}
		book_button.setTextColor(getResources().getColor(R.color.black));
		movie_button.setTextColor(getResources().getColor(R.color.black));
		music_button.setTextColor(getResources().getColor(R.color.black));
		transaction.commit();

	}

	private class clickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			FragmentTransaction transaction = manager.beginTransaction();
			switch (v.getId()) {
			case R.id.book:
				hideAllFragment();
				book_button.setTextColor(getResources().getColor(R.color.blue));
				transaction.show(bookList);
				transaction.commit();

				break;

			case R.id.movie:
				hideAllFragment();
				movie_button
						.setTextColor(getResources().getColor(R.color.blue));
				if (movieList == null) {
					movieList = new MovieList();
					transaction.add(R.id.content, movieList);
					transaction.commit();
				} else {
					transaction.show(movieList);
					transaction.commit();
				}
				break;
			case R.id.music:
				hideAllFragment();
				music_button
						.setTextColor(getResources().getColor(R.color.blue));
				if (musicList == null) {
					musicList = new MusicList();
					transaction.add(R.id.content, musicList);
					transaction.commit();
				} else {
					transaction.show(musicList);
					transaction.commit();
				}
				break;
			default:
				break;
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
