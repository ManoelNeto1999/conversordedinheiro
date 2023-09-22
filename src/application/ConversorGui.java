package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import entities.Cambio;

public class ConversorGui extends JFrame{
	public ConversorGui() {
		super("ConvertMoney");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		
		addComponents();
	}
	
	public void addComponents() {
		SpringLayout springLayout = new SpringLayout();
		JPanel convertPanel = new JPanel();
		convertPanel.setLayout(springLayout);
		
		//dinheiro em reais
		JLabel reaisLabel = new JLabel("Reais: ");
		reaisLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		JTextField reaisField = new JTextField(15);
		((AbstractDocument) reaisField.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int lenght, String text, AttributeSet attrs) throws BadLocationException{
				String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
				if (isValidNumber(newText)) {
					super.replace(fb, offset, lenght, text, attrs);
				} else {
					// se a entrada não for um numero valido, não faz nada
				}
			}
		});
		reaisField.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		springLayout.putConstraint(SpringLayout.WEST, reaisLabel, 35, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, reaisLabel, 50, SpringLayout.NORTH, convertPanel);
		springLayout.putConstraint(SpringLayout.WEST, reaisField, 135, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, reaisField, 50, SpringLayout.NORTH, convertPanel);
		
		convertPanel.add(reaisLabel);
		convertPanel.add(reaisField);
		
		//dinheiro em dollar
		JLabel dollarLabel = new JLabel("Dollares: ");
		dollarLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		JTextField dollarField = new JTextField(15);
		dollarField.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		springLayout.putConstraint(SpringLayout.WEST, dollarLabel, 35, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, dollarLabel, 250, SpringLayout.NORTH, convertPanel);
		springLayout.putConstraint(SpringLayout.WEST, dollarField, 135, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, dollarField, 250, SpringLayout.NORTH, convertPanel);
		
		convertPanel.add(dollarLabel);
		convertPanel.add(dollarField);
		
		//botao de converter
		JButton convertButton = new JButton("Converter");
		convertButton.setFont(new Font("Dialog", Font.BOLD, 18));
		springLayout.putConstraint(SpringLayout.WEST, convertButton, 135, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, convertButton, 150, SpringLayout.WEST, convertPanel);
		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String number = reaisField.getText();
				Cambio cambio = new Cambio();
				
				if (reaisField != null) {
					cambio = new Cambio(Double.parseDouble(number));
					String toStringResult = cambio.toString();
					dollarField.setText(toStringResult);
				}
			}
		});
		
		convertPanel.add(convertButton);
		
		this.getContentPane().add(convertPanel);
	}
	
	private boolean isValidNumber(String text) {
		try {
			// tenta converter o texto em um numero
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}