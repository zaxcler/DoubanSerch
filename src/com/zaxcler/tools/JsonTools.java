package com.zaxcler.tools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zaxcler.doubansearch.MovieList;
import com.zaxcler.infos.Avatars;
import com.zaxcler.infos.BookInfo;
import com.zaxcler.infos.MovieInfo;
import com.zaxcler.infos.MusicInfo;

public class JsonTools {

	public JsonTools() {
		
	}
	/**
	 *根基json数据得到图书list
	 * @param jsonData
	 * @return
	 */
	public static List<BookInfo> getBookInfoList(String jsonData){
		List<BookInfo> bookInfos=new ArrayList<>();
		try {
			JSONObject jsonObject=new JSONObject(jsonData);
			JSONArray jsonArray=jsonObject.getJSONArray("books");
			
			for (int i = 0; i < jsonArray.length(); i++) {
				BookInfo bookInfo=new BookInfo();
				//得到评分
				int score=jsonArray.getJSONObject(i).getJSONObject("rating").getInt("average");
				bookInfo.setScore(score);
				//得到作者，可能不只一个
				JSONArray author=jsonArray.getJSONObject(i).getJSONArray("author");
				String authors="";
				for (int j = 0; j < author.length(); j++) {	
					if(author.length()==1){
						authors=author.get(j).toString();
					}else {
						authors+=author.get(j).toString();	
					}									
				}
				bookInfo.setAuther(authors);
				//得到出版日期
				String pubdate=jsonArray.getJSONObject(i).getString("pubdate");
				bookInfo.setPubdate(pubdate);
				//得到书名
				String name=jsonArray.getJSONObject(i).getString("title");
				bookInfo.setName(name);
				//得到图片
				String photo_url=jsonArray.getJSONObject(i).getString("image");
				bookInfo.setPhoto_url(photo_url);
				//得到图书目录
				String describe=jsonArray.getJSONObject(i).getString("summary");
				bookInfo.setCatalog(describe);
				//得到翻译者，可能不只一个			 
				JSONArray translator=jsonArray.getJSONObject(i).getJSONArray("translator");
				String translators="";
				for (int j = 0; j < translator.length(); j++) {	
					if(translator.length()==1){
						translators=translator.get(j).toString();
					}else {
						translators=translator.get(j).toString()+" ";	
					}									
				}
				bookInfo.setTranslator(translators);
				//得到作者详细信息
				String author_info=jsonArray.getJSONObject(i).getString("author_intro");
				bookInfo.setAuthor_info(author_info);
				//得到id
				bookInfo.setId(jsonArray.getJSONObject(i).getInt("id"));
				//设置出版社
				bookInfo.setPublisher(jsonArray.getJSONObject(i).getString("publisher"));
				bookInfo.setPages(jsonArray.getJSONObject(i).getString("pages"));
				bookInfos.add(bookInfo);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookInfos;
		
	}
	
	public static List<MovieInfo> getMovieList(String jsonData){
		List<MovieInfo> movieInfos=new ArrayList<>();
		try {
			JSONObject jsonObject=new JSONObject(jsonData);
			JSONArray jsonArray=jsonObject.getJSONArray("subjects");
			for (int i = 0; i < jsonArray.length(); i++) {
				MovieInfo movieInfo=new MovieInfo();
				//得到评分
				int score=jsonArray.getJSONObject(i).getJSONObject("rating").getInt("average");
				movieInfo.setSocre(score);
				//得到电影类型，可能不止一个
				JSONArray array=jsonArray.getJSONObject(i).getJSONArray("genres");
				String genres="";
				for (int j = 0; j < array.length(); j++) {
					if (array.length()==1) {
						genres=array.get(j).toString();
					}else {
						genres+=array.get(j).toString()+" ";
					}					
				}
				movieInfo.setGenres(genres);
				//得到评价人数
				int collect_count=jsonArray.getJSONObject(i).getInt("collect_count");
				movieInfo.setCollect_count(collect_count);
				//得到主要演员表
				JSONArray actorArray=jsonArray.getJSONObject(i).getJSONArray("casts");
				List<Avatars> actorlist=new ArrayList<>();
				if (actorArray.length()>=1) {
					for (int j = 0; j < actorArray.length(); j++) {
						
						Avatars avatars=new Avatars();
						
						if (!("null".equals(actorArray.getJSONObject(j).getString("avatars")))) {
							avatars.setPhoto_url(actorArray.getJSONObject(j).getJSONObject("avatars").getString("large"));
						}						
						avatars.setName(actorArray.getJSONObject(j).getString("name"));
						avatars.setAlt(actorArray.getJSONObject(j).getString("alt"));
						avatars.setId(actorArray.getJSONObject(j).getString("id"));
						actorlist.add(avatars);
					}
				}
				
				movieInfo.setActors(actorlist);
				//得到电影名字
				movieInfo.setName(jsonArray.getJSONObject(i).getString("title"));
				//得到导演名字表
				JSONArray directorArray=jsonArray.getJSONObject(i).getJSONArray("directors");
				List<Avatars> directorlist=new ArrayList<>();
				if (directorArray.length()>=1) {
					for (int j = 0; j < directorArray.length(); j++) {
						Avatars avatars=new Avatars();
						if (!("null".equals(directorArray.getJSONObject(j).getString("avatars")))) {
							avatars.setPhoto_url(directorArray.getJSONObject(j).getJSONObject("avatars").getString("large"));
						}	
						avatars.setName(directorArray.getJSONObject(j).getString("name"));
						avatars.setAlt(directorArray.getJSONObject(j).getString("alt"));
						avatars.setId(directorArray.getJSONObject(j).getString("id"));
						directorlist.add(avatars);
					}
					movieInfo.setDirectors(directorlist);
				}
				
				
				//得到时间
				movieInfo.setTime(jsonArray.getJSONObject(i).getString("year"));
				//得到点海报
				
				movieInfo.setPhoto_url(jsonArray.getJSONObject(i).getJSONObject("images").getString("large"));
				//得到链接
				movieInfo.setDescribe(jsonArray.getJSONObject(i).getString("alt"));
				//得到id
				movieInfo.setId(jsonArray.getJSONObject(i).getString("id"));
				movieInfos.add(movieInfo);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieInfos;
		
	}
	public static List<MusicInfo> getMusicList(String jsonDate){
		List<MusicInfo> list=new ArrayList<>();
		
		try {
			JSONObject jsonObject=new JSONObject(jsonDate);
			JSONArray jsonArray=jsonObject.getJSONArray("musics");
			for (int i = 0; i < jsonArray.length(); i++) {
				MusicInfo info=new MusicInfo();
				info.setMusic_score(jsonArray.getJSONObject(i).getJSONObject("rating").getString("average"));
				JSONArray array=jsonArray.getJSONObject(i).getJSONArray("author");
				String authors="";
				for (int j = 0; j < array.length(); j++) {
					if (array.length()==1) {
						authors=array.getJSONObject(j).getString("name");
					}else {
						authors+=array.getJSONObject(j).getString("name")+" ";
					}
					
				}
				info.setMusic_author(authors);
				info.setMusic_id(jsonArray.getJSONObject(i).getString("id"));
				info.setMusic_name(jsonArray.getJSONObject(i).getString("title"));
				JSONArray tagarray=jsonArray.getJSONObject(i).getJSONArray("tags");
				String tags="";
				for (int j = 0; j < tagarray.length(); j++) {
					if (tagarray.length()==1) {
						tags=tagarray.getJSONObject(j).getString("name");
					}else {
						tags+=tagarray.getJSONObject(j).getString("name")+" ";
					}
					
				}
				info.setMusic_genres(tags);
				info.setMusic_photo(jsonArray.getJSONObject(i).getString("image"));
				list.add(info);
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
	}

}
