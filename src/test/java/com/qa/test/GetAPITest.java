package com.qa.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiURL;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpresponse;

	@BeforeMethod
	public void SetUp() throws ParseException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");

		url = serviceUrl + apiURL;
		System.out.println(" URL is : " + url);

	}

	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ParseException, IOException {
		restClient = new RestClient(); // calling get method
		closeableHttpresponse = restClient.get(url);

		// a. Status Code
		int statusCode = closeableHttpresponse.getCode();

		System.out.println("Status Code : " + statusCode);
		Assert.assertEquals(statusCode, 200, "Status Code is not 200");

		// b. JSON String
		String entityUtil = EntityUtils.toString(closeableHttpresponse.getEntity(), "UTF-8");
		System.out.println("Entity Util : " + entityUtil);

		// c. JSON Response
		JSONObject responseJson = new JSONObject(entityUtil);
		System.out.println("JSON response : " + responseJson);

		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per page is : " + perPageValue);

		// Single Value Assertion
		// perPageValue is into string cannot compare with int
		Assert.assertEquals(perPageValue, "6", "Per Page value does not match");
		// Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// converted string to int for comparision
		String totalString = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total is : " + totalString);
		int total = Integer.parseInt(totalString);
		Assert.assertEquals(total, 12, "Total value doesnot match");

		// JSON Array(multiple data in array)
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		System.out.println("Last Name --> " + lastName);
		Assert.assertEquals(lastName, RESPONSE_LASTNAME_0, "Last Name does not match");

		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		System.out.println("ID --> " + id);
		Assert.assertEquals(Integer.parseInt(id), RESPONSE_ID_0, "ID does not match");

		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		System.out.println("Avatar --> " + avatar);
		Assert.assertEquals(avatar, RESPONSE_AVATAR_0, "Avatar does not match");

		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		System.out.println("First Name --> " + firstName);
		Assert.assertEquals(firstName, RESPONSE_FIRSTNAME_0, "First Name does not match");

		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		System.out.println("Email --> " + email);
		Assert.assertEquals(email, RESPONSE_EMAIL_0, "First Name does not match");

		// d. Header Array
		Header[] headerArray = closeableHttpresponse.getHeaders();

		// e. Iterating Header Array
		Map<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("List of All headers : " + allHeaders);

	}
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ParseException, IOException {
		HashMap<String, String> headerMap = new HashMap<String, String>();
		//headerMap.put("User-Agent", "PostmanRuntime/7.36.1");
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("Host", "<calculated when request is sent>");
		
		restClient = new RestClient(); // calling get method
		closeableHttpresponse = restClient.get(url, headerMap);

		// a. Status Code
		int statusCode = closeableHttpresponse.getCode();

		System.out.println("Status Code : " + statusCode);
		Assert.assertEquals(statusCode, 200, "Status Code is not 200");

		// b. JSON String
		String entityUtil = EntityUtils.toString(closeableHttpresponse.getEntity(), "UTF-8");
		System.out.println("Entity Util : " + entityUtil);

		// c. JSON Response
		JSONObject responseJson = new JSONObject(entityUtil);
		System.out.println("JSON response : " + responseJson);

		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per page is : " + perPageValue);

		// Single Value Assertion
		// perPageValue is into string cannot compare with int
		Assert.assertEquals(perPageValue, "6", "Per Page value does not match");
		// Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// converted string to int for comparision
		String totalString = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total is : " + totalString);
		int total = Integer.parseInt(totalString);
		Assert.assertEquals(total, 12, "Total value doesnot match");

		// JSON Array(multiple data in array)
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		System.out.println("Last Name --> " + lastName);
		Assert.assertEquals(lastName, RESPONSE_LASTNAME_0, "Last Name does not match");

		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		System.out.println("ID --> " + id);
		Assert.assertEquals(Integer.parseInt(id), RESPONSE_ID_0, "ID does not match");

		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		System.out.println("Avatar --> " + avatar);
		Assert.assertEquals(avatar, RESPONSE_AVATAR_0, "Avatar does not match");

		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		System.out.println("First Name --> " + firstName);
		Assert.assertEquals(firstName, RESPONSE_FIRSTNAME_0, "First Name does not match");

		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		System.out.println("Email --> " + email);
		Assert.assertEquals(email, RESPONSE_EMAIL_0, "First Name does not match");

		// d. Header Array
		Header[] headerArray = closeableHttpresponse.getHeaders();

		// e. Iterating Header Array
		Map<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("List of All headers : " + allHeaders);

	}
	
	
}
