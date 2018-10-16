package com.jmc.scm.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.map.ObjectMapper;

public class HttpUtil {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> handleRequest(String requestUrl,
			String content) {
		Map<String, Object> result = null;
		HttpClient client = null;
		HttpPost postRequest = null;
		ObjectMapper mapper = null;
		HttpResponse postResponse = null;
		HttpEntity responseEntity = null, requestEntity = null;
		String responseString = null;

		try {
			client = HttpClients.createDefault();

			postRequest = new HttpPost(requestUrl);
			requestEntity = new StringEntity(content, Charsets.UTF_8);
			postRequest.addHeader(HTTP.CONTENT_TYPE, "application/json");

			postRequest.setEntity(requestEntity);
			postResponse = client.execute(postRequest);
			responseEntity = postResponse.getEntity();

			mapper = new ObjectMapper();
			responseString = IOUtils.toString(responseEntity.getContent(),
					"utf-8");
			System.out.println(responseString);
			result = mapper.readValue(responseString, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			result = new HashMap<String, Object>();
			result.put("status", 300);
			result.put("msg", e.getMessage());
		}
		return result;
	}

	public static String handleRequest(String requestUrl, String content,
			String header) {
		HttpClient client = null;
		HttpPost postRequest = null;
		HttpResponse postResponse = null;
		HttpEntity responseEntity = null, requestEntity = null;
		String responseString = null;

		try {
			client = HttpClients.createDefault();

			postRequest = new HttpPost(requestUrl);
			requestEntity = new StringEntity(content, Charsets.UTF_8);
			postRequest.addHeader(HTTP.CONTENT_TYPE, header);

			postRequest.setEntity(requestEntity);
			postResponse = client.execute(postRequest);
			responseEntity = postResponse.getEntity();

			responseString = IOUtils.toString(responseEntity.getContent(),
					"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			responseString = "";
		}
		return responseString;
	}

	public static String handleRequest(String requestUrl, String content,
			String header, String username, String password) {
		BasicCredentialsProvider credentialProvider = null;

		credentialProvider = new BasicCredentialsProvider();
		credentialProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(username, password));

		CloseableHttpClient client = null;
		HttpPost postRequest = null;
		HttpResponse postResponse = null;
		HttpEntity responseEntity = null, requestEntity = null;
		String responseString = null;

		try {
			client = HttpClientBuilder.create()
					.setDefaultCredentialsProvider(credentialProvider).build();

			postRequest = new HttpPost(requestUrl);
			requestEntity = new StringEntity(content, Charsets.UTF_8);
			postRequest.addHeader(HTTP.CONTENT_TYPE, header);

			postRequest.setEntity(requestEntity);
			// postRequest.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
			postResponse = client.execute(postRequest);
			responseEntity = postResponse.getEntity();

			responseString = IOUtils.toString(responseEntity.getContent(),
					"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			responseString = "";
		}
		return responseString;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> handleGetRequest(String requestUrl) {
		Map<String, Object> result = null;

		HttpClient client = null;
		HttpGet getRequest = null;
		HttpResponse getResponse = null;
		HttpEntity responseEntity = null;
		String responseString = null;
		ObjectMapper mapper = null;

		try {
			client = HttpClientBuilder.create().build();

			getRequest = new HttpGet(requestUrl);

			getResponse = client.execute(getRequest);
			responseEntity = getResponse.getEntity();

			responseString = IOUtils.toString(responseEntity.getContent(),
					"utf-8");
			mapper = new ObjectMapper();

			result = mapper.readValue(responseString, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			responseString = "";
		}

		return result;
	}

}
