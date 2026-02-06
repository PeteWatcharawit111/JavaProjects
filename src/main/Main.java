package main;

import java.io.IOException;
import java.util.Map;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) throws IOException {
		double inputAmount = 10000;
		String inputCurrency = "THB";
		String outputCurrency = "PHP";
		String apiKey = "10e236b66fb61cc1cb64e4e6";
		
		APIHandler apiHandler = new APIHandler(apiKey, inputCurrency);
		
		System.out.println("Hello World");

		// If you want to use the raw data elsewhere:
		String allFields = apiHandler.getAllFields();
		System.out.println(allFields);

		Map<String, Double> conversionRates = apiHandler.parseConversionRates(allFields);
		System.out.println(conversionRates.get("PHP"));
		
		double outputAmount = inputAmount * conversionRates.get(outputCurrency); 

		SwingUtilities.invokeLater(() -> {
            new CurrencyUI(inputCurrency, outputCurrency, inputAmount, outputAmount).setVisible(true);
        });
		
		/*CurrencyConverter currencyConverter = new CurrencyConverter();
		double amount = currencyConverter.convertCurrency("PHP", "USD", 1000);
		System.out.println(amount);
		amount = currencyConverter.convertCurrency("USD", "PHP", 1000);
		System.out.println(amount);*/
	}

}