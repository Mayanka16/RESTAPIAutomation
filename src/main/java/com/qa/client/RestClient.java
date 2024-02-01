package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method without Headers

	public CloseableHttpResponse get(String url) throws IOException, ParseException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // http get request

		CloseableHttpResponse closeableHttpresponse = httpClient.execute(httpGet); // hit the URL

		return closeableHttpresponse;
	}

	// 2. GET Method with Headers

	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws IOException, ParseException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // http get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}

		System.out.println("Entry Values --> " + httpGet);
		CloseableHttpResponse closeableHttpresponse = httpClient.execute(httpGet); // hit the URL

		return closeableHttpresponse;

	}
}
