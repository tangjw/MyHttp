package com.zonsim.myhttp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.Logger;
import com.zonsim.myhttp.R;
import com.zonsim.myhttp.net.HttpLoader;

/**
 * CopyRight
 * Created by tang-jw on 5/28.
 */

public class ImageActivity extends AppCompatActivity {

private Button mLoad;
private Button mLoad1;
private ImageLoader imageLoader;

private String url = "http://m2.urrpic.info/img/upload/image/20160522/52200032164.jpg";
private ImageView mImageView;
private NetworkImageView mViewById;

@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.activity_image);
	
	mLoad = (Button) findViewById(R.id.button3);
	mLoad1 = (Button) findViewById(R.id.button4);
	mViewById = (NetworkImageView) findViewById(R.id.nv_img);
	
	
	mImageView = (ImageView) findViewById(R.id.iv_img);
	
	imageLoader = HttpLoader.getImageLoader();
	
	
	mViewById.setDefaultImageResId(R.mipmap.ic_launcher);
	
	mViewById.setImageUrl(url, imageLoader);
	
	mLoad.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			getImage();
			
			
		}
	});
	
	mLoad1.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			myImageRequest();
		}
	});
	
	
}

/**
 * volley 提供的网络图片请求
 */
private void myImageRequest() {
	ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
		@Override
		public void onResponse(Bitmap bitmap) {
//			bitmap.get
			mImageView.setImageBitmap(bitmap);
			Logger.d(bitmap.getRowBytes() * bitmap.getHeight() + "");
		}
	}, 200, 200, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
		@Override
		public void onErrorResponse(VolleyError volleyError) {
			
		}
	});
	
	RequestQueue requestQueue = Volley.newRequestQueue(ImageActivity.this);
	requestQueue.add(imageRequest);
}

/**
 * 封装好的imageloader
 */
private void getImage() {
	
	ImageListener imageListener = ImageLoader.getImageListener(mImageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
	//可以设置参数宽高,压缩类型等
	imageLoader.get(url, imageListener);
	
	
}
}
