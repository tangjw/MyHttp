package com.zonsim.myhttp.net;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.CountingRequestBody;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * post上传文件
 * Created by tang-jw on 2016/5/30.
 */
public class OkPostFile extends OkHttpRequest {
	private static MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
	private File file;
	private MediaType mediaType;
	
	public OkPostFile(String url, Object tag, Map<String, String> params, Map<String, String> headers, File file, MediaType mediaType) {
		super(url, tag, params, headers);
		this.file = file;
		this.mediaType = mediaType;
		if(this.file == null) {
//			Exceptions.illegalArgument("the file can not be null !", new Object[0]);
		}
		
		if(this.mediaType == null) {
			this.mediaType = MEDIA_TYPE_STREAM;
		}
		
	}
	
	protected RequestBody buildRequestBody() {
		return RequestBody.create(this.mediaType, this.file);
	}
	
	protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
		if(callback == null) {
			return requestBody;
		} else {
			CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
				public void onRequestProgress(final long bytesWritten, final long contentLength) {
					OkHttpUtils.getInstance().getDelivery().execute(new Runnable() {
						public void run() {
							callback.inProgress((float)bytesWritten * 1.0F / (float)contentLength);
						}
					});
				}
			});
			return countingRequestBody;
		}
	}
	
	protected Request buildRequest(RequestBody requestBody) {
		return this.builder.post(requestBody).build();
	}
}
