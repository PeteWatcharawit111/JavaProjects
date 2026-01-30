package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIHandler {
	public APIHandler() throws IOException {
		// Setting URL
		String url_str = "https://v6.exchangerate-api.com/v6/10e236b66fb61cc1cb64e4e6/latest/USD";

		// Making Request
		URL url = new URL(url_str);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();
		
		// Read response as text
		BufferedReader reader = new BufferedReader(
		        new InputStreamReader((InputStream) request.getContent())
		);

		StringBuilder response = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
		    response.append(line);
		}

		reader.close();

		// Convert response to String
		String jsonResponse = response.toString();

		// VERY SIMPLE extraction (example: "result":"success")
		String req_result = null;

		if (jsonResponse.contains("\"result\"")) {
		    int start = jsonResponse.indexOf("\"result\"") + 9;
		    int end = jsonResponse.indexOf("\"", start);
		    req_result = jsonResponse.substring(start, end);
		}

		System.out.println("Result: " + req_result);
	}
}
