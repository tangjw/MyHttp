package com.zonsim.myhttp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;
import com.zonsim.myhttp.R;
import com.zonsim.myhttp.bean.BaseResponseBean;
import com.zonsim.myhttp.net.HttpLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tang-jw on 2016/5/27.
 */
public class PostActivity extends AppCompatActivity {
	
	private Button mViewById;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		mViewById = (Button) findViewById(R.id.btn_submit1);
		
		mViewById.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				submitAdd();
				
			}
		});
		checkNum();
		
	}
	
	private void submitAdd() {
		String url = "http://118.145.26.214:8080/yixue_edu/lianyi/EduMail/saveMail.do";
		
		Map<String, String> params = new HashMap<>();
		
		params.put("name", "暂时");
		params.put("phoneNum", "2222");
		params.put("zipCode", "213112");
		params.put("address", "dsedesfdes");
		params.put("ticket", "123213213");
		
		HttpLoader.post(url, params, BaseResponseBean.class, 120, new HttpLoader.ResponseListener() {
			@Override
			public void onGetResponseSuccess(int requestCode, BaseResponseBean response) {
				com.orhanobut.logger.Logger.i(response.getStatus()+"hahaha");
			}
			
			@Override
			public void onGetResponseError(int requestCode, VolleyError error) {
				
			}
		});
		
	}
	
	private void checkNum() {
		String url = "http://118.145.26.214:8080/yixue_edu/lianyi/EduApplyInfo/getApplyInfoByTicket.do";
		Map<String, String> params = new HashMap<>();
		
		params.put("ticketNum","85160527001");
		
		Request request = HttpLoader.get(url, params, BaseResponseBean.class, 101, new HttpLoader.ResponseListener() {
			@Override
			public void onGetResponseSuccess(int requestCode, BaseResponseBean response) {
				Logger.i(response.getProName());
			}
			
			@Override
			public void onGetResponseError(int requestCode, VolleyError error) {
				
			}
		});
		
		request.setTag("1111");
		
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		HttpLoader.cancelRequest("1111");
	}
}
