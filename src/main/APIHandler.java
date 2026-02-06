package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class APIHandler {

	private final String jsonResponse;

	public APIHandler(String apiKey, String currency) throws IOException {

		String urlStr =
				"https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + currency;

		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(10_000);
		conn.setReadTimeout(10_000);

		int status = conn.getResponseCode();

		InputStream stream =
				(status >= 200 && status < 300)
						? conn.getInputStream()
						: conn.getErrorStream();

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream, StandardCharsets.UTF_8)
		);

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();

		jsonResponse = sb.toString();
		
		String result = extractStringValue(jsonResponse, "result");
		
		if ("success".equals(result)) {
			System.out.println("API call successful.");
			System.out.println("=== ALL FIELD VALUES ===");
			System.out.println(jsonResponse);
		} else {
			System.out.println("API call failed.");
			System.out.println(jsonResponse);
		}
	}

	/**
	 * Returns the full JSON response (ALL fields & values)
	 */
	public String getAllFields() {
		return jsonResponse;
	}

	/**
	 * Extracts a string value like: "key":"value"
	 */
	private String extractStringValue(String json, String key) {
		String pattern = "\"" + key + "\":\"";
		int start = json.indexOf(pattern);
		if (start == -1) return null;

		start += pattern.length();
		int end = json.indexOf("\"", start);
		if (end == -1) return null;

		return json.substring(start, end);
	}

	public Map<String, Double> parseConversionRates(String json) {
		Map<String, Double> map = new HashMap<>();

		// Find "conversion_rates":{ ... }
		String key = "\"conversion_rates\":{";
		int start = json.indexOf(key);
		if (start == -1) return map;

		start += key.length();
		int end = json.indexOf("}", start);
		if (end == -1) return map;

		// Extract the inner content
		String body = json.substring(start, end);

		// Split key:value pairs
		String[] entries = body.split(",");

		for (String entry : entries) {
			String[] kv = entry.split(":");
			if (kv.length != 2) continue;

			String currency = kv[0].replace("\"", "").trim();
			double rate = Double.parseDouble(kv[1].trim());

			map.put(currency, rate);
		}

		return map;
	}

}