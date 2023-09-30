package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import entities.Dollar;
import entities.Euro;
import entities.Libra;
import entities.Peso;
import entities.Real;

public class ConversorGui extends JFrame{
	public ConversorGui() {
		super("ConvertMoney");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		
		addComponents();
	}
	
	public void addComponents() {
		SpringLayout springLayout = new SpringLayout();
		JPanel convertPanel = new JPanel();
		convertPanel.setLayout(springLayout);
		
		String coins[] = {"Real", "Dollar", "Euro", "Libra", "Peso"};
		String coins1[] = {"Dollar", "Euro", "Libra", "Peso"};
		
		//dinheiro em moeda inicial
		JLabel coinLabel = new JLabel("Reais: ");
		coinLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		JTextField coinField = new JTextField(15);
		((AbstractDocument) coinField.getDocument()).setDocumentFilter(new DocumentFilter() {
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
		coinField.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		springLayout.putConstraint(SpringLayout.WEST, coinLabel, 35, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, coinLabel, 100, SpringLayout.NORTH, convertPanel);
		springLayout.putConstraint(SpringLayout.WEST, coinField, 135, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, coinField, 100, SpringLayout.NORTH, convertPanel);
		
		convertPanel.add(coinLabel);
		convertPanel.add(coinField);
		
		// campo para selecionar a moeda inicial
		JLabel selectionLabel1 = new JLabel("Escolha a moeda inicial: ");
		selectionLabel1.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		JComboBox selectionBox1 = new JComboBox(coins);
		selectionBox1.setSelectedItem(coins[0]);
		JComboBox selectionBox2 = new JComboBox(coins1);
		selectionBox1.setFont(new Font("Dialog", Font.PLAIN, 18));
		selectionBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectionOption = (String) selectionBox1.getSelectedItem();
				coinLabel.setText(selectionOption + ": ");
				
				//atualiza o segundo combobox de acordo com a seleção do primeiro
				selectionBox2.removeAllItems();
				String test[] = isNewCoins(coins, (String) selectionBox1.getSelectedItem());
				for (int i=0; i<test.length; i++) {
					if (test[i] != null) {
						selectionBox2.addItem(test[i]);
					}
				}
			}
		});
		
		springLayout.putConstraint(SpringLayout.WEST, selectionLabel1, 35, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, selectionLabel1, 20, SpringLayout.NORTH, convertPanel);
		springLayout.putConstraint(SpringLayout.WEST, selectionBox1, 35, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, selectionBox1, 50, SpringLayout.NORTH, convertPanel);
		
		convertPanel.add(selectionLabel1);
		convertPanel.add(selectionBox1);
		
		//dinheiro em moeda convertida
		JLabel coinConvertLabel = new JLabel("Dollar: ");
		coinConvertLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		JTextField coinConvertField = new JTextField(15);
		coinConvertField.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		springLayout.putConstraint(SpringLayout.WEST, coinConvertLabel, 35, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, coinConvertLabel, 400, SpringLayout.NORTH, convertPanel);
		springLayout.putConstraint(SpringLayout.WEST, coinConvertField, 135, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, coinConvertField, 400, SpringLayout.NORTH, convertPanel);
		
		convertPanel.add(coinConvertLabel);
		convertPanel.add(coinConvertField);
		
		// campo para selecionar para qual moeda deseja fazer o cambio
		JLabel selectionLabel2 = new JLabel("Escolha para qual moeda deseja converter:");
		selectionLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		//JComboBox selectionBox2 = new JComboBox(isNewCoins(coins, (String) selectionBox1.getSelectedItem()));
		selectionBox2.setFont(new Font("Dialog", Font.PLAIN, 18));
		selectionBox2.setSelectedItem(coins[1]);
		selectionBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectionOption = (String) selectionBox2.getSelectedItem();
				coinConvertLabel.setText(selectionOption + ": ");
			}
		});
		
		springLayout.putConstraint(SpringLayout.WEST, selectionLabel2, 35, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, selectionLabel2, 200, SpringLayout.NORTH, convertPanel);
		springLayout.putConstraint(SpringLayout.WEST,selectionBox2, 35, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, selectionBox2, 230, SpringLayout.NORTH, convertPanel);
		
		convertPanel.add(selectionLabel2);
		convertPanel.add(selectionBox2);
		
		//botao de converter
		JButton convertButton = new JButton("Converter");
		convertButton.setFont(new Font("Dialog", Font.BOLD, 18));
		springLayout.putConstraint(SpringLayout.WEST, convertButton, 135, SpringLayout.WEST, convertPanel);
		springLayout.putConstraint(SpringLayout.NORTH, convertButton, 300, SpringLayout.WEST, convertPanel);
		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String number = coinField.getText();
				Real real = new Real();
				Dollar dollar = new Dollar();
				Euro euro = new Euro();
				Libra libra = new Libra();
				Peso peso = new Peso();
				
				if (coinField != null && selectionBox1.getSelectedItem().equals("Real")) {
					real = new Real(Double.parseDouble(number));
					if (selectionBox2.getSelectedItem().equals("Dollar")) {
						String toStringResult = real.toString();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Euro")) {
						String toStringResult = real.toString1();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Libra")) {
						String toStringResult = real.toString2();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Peso")) {
						String toStringResult = real.toString3();
						coinConvertField.setText(toStringResult);
					}
				}
				else if (coinField != null && selectionBox1.getSelectedItem().equals("Dollar")) {
					dollar = new Dollar(Double.parseDouble(number));
					if (selectionBox2.getSelectedItem().equals("Real")) {
						String toStringResult = dollar.toString();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Euro")) {
						String toStringResult = dollar.toString1();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Libra")) {
						String toStringResult = dollar.toString2();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Peso")) {
						String toStringResult = dollar.toString3();
						coinConvertField.setText(toStringResult);
					}
				}
				else if (coinField != null && selectionBox1.getSelectedItem().equals("Euro")) {
					euro = new Euro(Double.parseDouble(number));
					if (selectionBox2.getSelectedItem().equals("Real")) {
						String toStringResult = euro.toString();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Dollar")) {
						String toStringResult = euro.toString1();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Libra")) {
						String toStringResult = euro.toString2();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Peso")) {
						String toStringResult = euro.toString3();
						coinConvertField.setText(toStringResult);
					}
				}
				else if (coinField != null && selectionBox1.getSelectedItem().equals("Libra")) {
					libra = new Libra(Double.parseDouble(number));
					if (selectionBox2.getSelectedItem().equals("Real")) {
						String toStringResult = libra.toString();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Dollar")) {
						String toStringResult = libra.toString1();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Euro")) {
						String toStringResult = libra.toString2();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Peso")) {
						String toStringResult = libra.toString3();
						coinConvertField.setText(toStringResult);
					}
				}
				else if (coinField != null && selectionBox1.getSelectedItem().equals("Peso")) {
					peso = new Peso(Double.parseDouble(number));
					if (selectionBox2.getSelectedItem().equals("Real")) {
						String toStringResult = peso.toString();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Dollar")) {
						String toStringResult = peso.toString1();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Euro")) {
						String toStringResult = peso.toString2();
						coinConvertField.setText(toStringResult);
					}
					else if (selectionBox2.getSelectedItem().equals("Libra")) {
						String toStringResult = peso.toString3();
						coinConvertField.setText(toStringResult);
					}
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
	
	private String[] isNewCoins(String coins[], String option) {
		String coins2[] = new String[5]; 
		for (int i = 0; i<coins.length; i++) {
			if (coins[i] != option) {
				coins2[i] = coins[i];
			}
		}
		return coins2;
	}

}