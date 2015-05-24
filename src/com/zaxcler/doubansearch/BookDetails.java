package com.zaxcler.doubansearch;

import com.zaxcler.infos.BookInfo;
import com.zaxcler.tools.AsyncDownload;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetails extends Activity{
	private ImageView book_photo;
	private TextView book_name;
	private TextView book_author;
	private TextView book_score;
	private TextView book_pubdate_publisher;
	private TextView pages;
	private TextView author_info;
	private TextView book_catalog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_bookdetails);
		book_photo=(ImageView) findViewById(R.id.book_photo);
		book_name=(TextView) findViewById(R.id.book_name);
		book_author=(TextView) findViewById(R.id.book_author);
		book_score=(TextView) findViewById(R.id.book_score);
		book_pubdate_publisher=(TextView) findViewById(R.id.book_pubdate_publisher);
		pages=(TextView) findViewById(R.id.book_pages);
		author_info=(TextView) findViewById(R.id.book_author_info);
		book_catalog=(TextView) findViewById(R.id.book_catalog);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		BookInfo bookInfo=(BookInfo) bundle.getSerializable("bookinfo");
		new AsyncDownload(bookInfo.getPhoto_url(), book_photo).execute();
		book_name.setText(bookInfo.getName());
		book_author.setText("作者："+bookInfo.getAuther());
		book_score.setText("评分："+bookInfo.getScore()+"/10");
		book_pubdate_publisher.setText("出版时间和出版社："+bookInfo.getPubdate()+" "+bookInfo.getPublisher());
		pages.setText("共"+bookInfo.getPages()+"页");
		author_info.setText("作者简介："+bookInfo.getAuthor_info()+"\n");
		book_catalog.setText("主要内容："+bookInfo.getCatalog());
		
	}

}
