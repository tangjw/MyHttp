package com.zonsim.myhttp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.Logger;
import com.zonsim.myhttp.activity.PostActivity;
import com.zonsim.myhttp.bean.BaseResponseBean;
import com.zonsim.myhttp.bean.TestBean;
import com.zonsim.myhttp.net.HttpLoader;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
	
	private static final String TAG = "MainActivity";
	private final String testUrl = "http://192.168.1.57:8080/okhttp.txt";
	private Button mOkHttp;
	private final OkHttpClient client = new OkHttpClient();
	private Button mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mOkHttp = (Button) findViewById(R.id.button);
		mButton = (Button) findViewById(R.id.button2);
		
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, PostActivity.class));
			}
		});
		
		mOkHttp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				asynchttp();
				/*new Thread(new Runnable() {
					@Override
					public void run() {
						try {
//							execute();
							enqueue();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();*/
				
				useVolley();
			}
		});
		
		
	}
	
	
	/**
	 * okhttp网络框架  同步
	 */
	private void execute() throws IOException {
		
		Request request = new Request.Builder()
				.url(testUrl)
				.build();
		
		Response response = client.newCall(request).execute();
		
		if (response.isSuccessful()) {
			System.out.println(response.code());
			System.out.println(response.body().string());
		}
		
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				
			}
			
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					System.out.println(response.code());
					String string = response.body().string();
					System.out.println(string);
					System.out.println(Thread.currentThread().getName());
					Log.d(TAG, string);
					Logger.d(string);
				}
			}
		});
		
	}
	
	/**
	 * okhttp网络框架  异步请求
	 */
	private void enqueue() throws IOException {
		
		Request request = new Request.Builder()
				.url(testUrl)
				.build();
		
		Response response = client.newCall(request).execute();
		
		if (response.isSuccessful()) {
			System.out.println(response.code());
			System.out.println(response.body().string());
		}
		
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				
			}
			
			@Override
			public void onResponse(Call call, final Response response) throws IOException {
				if (response.isSuccessful()) {
					System.out.println(response.code());
					final String string = response.body().string();
					System.out.println(string);
					
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
						}
					});
					Log.d(TAG, string);
					Logger.i(string);
					Logger.json(string);
					
				}
			}
		});
		
	}
	
	/**
	 * android-async-http
	 */
	private void asynchttp() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(testUrl, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				Logger.d(new String(responseBody));
				Toast.makeText(MainActivity.this, new String(responseBody), Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				
			}
		});
	}
	
	
	private void useVolley() {
		StringRequest stringRequest = new StringRequest(testUrl,
				new com.android.volley.Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Logger.d(response);
					}
				}, new com.android.volley.Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
		});
		
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		
		
//		requestQueue.add(stringRequest);
		
		/**
		 * volley Get请求
		 */
		HttpLoader.get(testUrl, null, TestBean.class, 100, new HttpLoader.ResponseListener() {
			@Override
			public void onGetResponseSuccess(int requestCode, BaseResponseBean response) {
				if (requestCode == 100) {
					TestBean result = (TestBean) response;
					String email = result.getInfo().getProgrammers().get(0).getEmail();
					Toast.makeText(MainActivity.this, email, Toast.LENGTH_SHORT).show();
					Logger.i(email);
				}
			}
			
			@Override
			public void onGetResponseError(int requestCode, VolleyError error) {
				if (requestCode == 100) {
					Logger.e(error.getMessage());
				}
			}
		},false);
		
		
		
		
		
		
		
		
	}
}
