package com.zonsim.myhttp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.orhanobut.logger.Logger;
import com.zonsim.myhttp.R;
import com.zonsim.myhttp.bean.BaseResponseBean;
import com.zonsim.myhttp.bean.NewsListBean;
import com.zonsim.myhttp.net.HttpLoader;
import com.zonsim.myhttp.utils.DensityUtil;

import java.util.List;

/**
 * CopyRight
 * Created by tang-jw on 5/29.
 */
public class NewsActivity extends AppCompatActivity {

private ListView mNewsList;
private String serverUrl = "http://118.145.26.215:8090/edu";
private String newsUrl = "http://192.168.1.57:8080/newslist.txt";
private List<NewsListBean.InfoBean> mNews;

@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.activity_news);
	
	mNewsList = (ListView) findViewById(R.id.lv_news);
	
	getNewsList();
	
}

/**
 * 从服务器获得新闻列表
 */
private void getNewsList() {
	HttpLoader.get(newsUrl, null, NewsListBean.class, 198, new HttpLoader.ResponseListener() {
		@Override
		public void onGetResponseSuccess(int requestCode, BaseResponseBean response) {
			if (requestCode == 198) {
				NewsListBean bean = (NewsListBean) response;
				mNews = bean.getInfo();
				
				mNewsList.setAdapter(new NewsAdapter());
				Logger.json(response.toString());
			}
		}
		
		@Override
		public void onGetResponseError(int requestCode, VolleyError error) {
			
		}
	});
	
}

/**
 * 新闻的ListView
 */
private class NewsAdapter extends BaseAdapter {
	@Override
	public int getCount() {
		return mNews.size();
	}
	
	@Override
	public NewsListBean.InfoBean getItem(int position) {
		return mNews.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(NewsActivity.this, R.layout.item_newslist, null);
			holder = new ViewHolder();
			holder.newImg = (ImageView) convertView.findViewById(R.id.iv_new);
			holder.newTitle = (TextView) convertView.findViewById(R.id.tv_title);
			holder.newDetail = (TextView) convertView.findViewById(R.id.tv_detail);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.newTitle.setText(mNews.get(position).getTitle());
		holder.newDetail.setText(mNews.get(position).getSummary());
		ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(holder.newImg,
				R.mipmap.ic_launcher, R.mipmap.ic_launcher);
		
		HttpLoader.getImageLoader().get(serverUrl + mNews.get(position).getSummary_image(), imageListener,
				DensityUtil.dip2px(getApplicationContext(),120),DensityUtil.dip2px(getApplicationContext(),100));
		
		return convertView;
	}
}

private static class ViewHolder {
	ImageView newImg;
	TextView newTitle;
	TextView newDetail;
}
	
	
}
