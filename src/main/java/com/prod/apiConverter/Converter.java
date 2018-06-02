package com.prod.apiConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Converter {

	static HashMap<String, String> convert(String link) {
		HttpURLConnection con = null;
		try {
			URL url = new URL(link);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			int status = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			JSONArray data = (JSONArray) new JSONParser().parse(content.toString());
			ArrayList<JSONObject> filtered = new ArrayList<JSONObject>();
			
			for(Object object : data) {
				JSONObject jsonObject = (JSONObject) object;
					
				if(jsonObject.containsKey("Long Running Query"))
					filtered.add(jsonObject);
			}
			
			filtered.forEach(System.out::println);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return null;
	}

}
