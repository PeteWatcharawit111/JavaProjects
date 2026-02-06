package main;

import java.awt.*;
import javax.swing.*;

public class CurrencyUI extends JFrame {
	private String inputCurrency;
	private double inputAmount;
	
	private String outputCurrency;
	private double outputAmount;

    // Input fields
    private JTextField inputCurrencyName;
    private JTextField inputCurrencyValue;

    // Output fields
    private JTextField outputCurrencyName;
    private JTextField outputCurrencyValue;

    public CurrencyUI(String inputCurrencyName, String outputCurrencyName, double inputCurrencyValue, double outputCurrencyValue) {
    	inputCurrency = inputCurrencyName;
        inputAmount = inputCurrencyValue;
        outputCurrency = outputCurrencyName;
        outputAmount = outputCurrencyValue;
        
    	setTitle("Currency Converter UI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        setLayout(new BorderLayout(10, 10));

        // Panels
        add(createImagePanel(), BorderLayout.WEST);
        add(createInputPanel(), BorderLayout.CENTER);
        add(createOutputPanel(), BorderLayout.EAST);     
        
    }

    // Panel 1: Image Panel
    private JPanel createImagePanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(180, 300));
        panel.setBorder(BorderFactory.createTitledBorder("Image"));

        ImageIcon imageIcon = new ImageIcon("pictures/currency_converter.jpg"); // <-- your JPG file
        JLabel imageLabel = new JLabel(imageIcon);

        panel.add(imageLabel);
        return panel;
    }

    // Panel 2: Input Currency Panel
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Input Currency"));

        inputCurrencyName = new JTextField();
        inputCurrencyValue = new JTextField();
        
        inputCurrencyName.setText(inputCurrency);
        inputCurrencyValue.setText(String.valueOf(inputAmount));

        panel.add(new JLabel("Currency Name:"));
        panel.add(inputCurrencyName);
        panel.add(new JLabel("Amount:"));
        panel.add(inputCurrencyValue);

        return panel;
    }

    // Panel 3: Output Currency Panel
    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Output Currency"));

        outputCurrencyName = new JTextField();
        outputCurrencyValue = new JTextField();
        
        outputCurrencyName.setText(outputCurrency);
        outputCurrencyValue.setText(String.valueOf(outputAmount));

        outputCurrencyName.setEditable(false);
        outputCurrencyValue.setEditable(false);

        panel.add(new JLabel("Currency Name:"));
        panel.add(outputCurrencyName);
        panel.add(new JLabel("Amount:"));
        panel.add(outputCurrencyValue);

        return panel;
    }
}