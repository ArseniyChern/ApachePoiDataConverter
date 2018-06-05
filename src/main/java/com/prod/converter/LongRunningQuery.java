package com.prod.converter;

import org.json.simple.JSONObject;

public class LongRunningQuery {
	
	private String data1;
	
	private String data2;
	
	private String data3;
	
	private String data4;
	
	private String data5;
	
	private LongRunningQuery() {
		
	}

	public String getData1() {
		return data1;
	}

	public LongRunningQuery setData1(String data1) {
		this.data1 = data1;
		return this;
	}

	public String getData2() {
		return data2;
	}

	public LongRunningQuery setData2(String data2) {
		this.data2 = data2;
		return this;
	}

	public String getData3() {
		return data3;
	}

	public LongRunningQuery setData3(String data3) {
		this.data3 = data3;
		return this;
	}

	public String getData4() {
		return data4;
	}

	public LongRunningQuery setData4(String data4) {
		this.data4 = data4;
		return this;
	}

	public String getData5() {
		return data5;
	}

	public LongRunningQuery setData5(String data5) {
		this.data5 = data5;
		return this;
	}
	
	public static LongRunningQuery fromJSONObject(JSONObject object) {
		return new LongRunningQuery().setData1(object.get("data1").toString())
				.setData2(object.get("data2").toString())
				.setData3(object.get("data3").toString())
				.setData4(object.get("data4").toString())
				.setData5(object.get("data5").toString());
						
	}
	
	
	

}
