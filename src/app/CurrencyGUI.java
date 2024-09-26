package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class CurrencyGUI extends JFrame {

	
	private JTextField amountTextField,resultTextField;
	private JLabel fromSymbol,toSymbol;
	private JComboBox<String> fromCurrCombo,toCurrCombo;
	private static String[] currencyName = CurrencyConverter.getCurrencySymbols();
	
	public CurrencyGUI() {
		
		this.setSize(350,600);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Currency Converter App");
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		
		
		initComponent();
			
		this.setVisible(true);
	}

	private void initComponent() {
		
		JLabel l1 = new JLabel("Contact");
		l1.setBounds(220,20,80,20);
		l1.setFont(new Font("Arial",Font.PLAIN,20));
		l1.setForeground(new Color(0,204,102));
		this.add(l1);
		
		JLabel l2 = new JLabel("Currency");
		l2.setBounds(20,50,150,25);
		l2.setFont(new Font("Arial",Font.BOLD,25));
		l2.setForeground(new Color(0,102,204));
		this.add(l2);
		
		JLabel l3 = new JLabel("Converter");
		l3.setBounds(20,75,150,25);
		l3.setFont(new Font("Arial",Font.BOLD,25));
		l3.setForeground(new Color(0,102,204));
		this.add(l3);
		
		JLabel l4 = new JLabel("From");
		l4.setBounds(20,120,50,25);
		l4.setFont(new Font("Arial",Font.PLAIN,15));
		l4.setForeground(Color.BLACK);
		this.add(l4);
		
		fromCurrCombo = new JComboBox<>(currencyName);
		fromCurrCombo.setBounds(20,155,295,30);
		fromCurrCombo.setSelectedIndex(0);
		fromCurrCombo.setForeground(Color.black);
		fromCurrCombo.setFont(new Font("Arial",Font.PLAIN,13));
		fromCurrCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				fromSymbol.setText((String)fromCurrCombo.getSelectedItem());
				amountTextField.setText("");
				resultTextField.setText("");

				
			}
			
		});
		this.add(fromCurrCombo);
		
		JLabel l5 = new JLabel("To");
		l5.setBounds(20,210,50,25);
		l5.setFont(new Font("Arial",Font.PLAIN,15));
		l5.setForeground(Color.BLACK);
		this.add(l5);
		
		toCurrCombo = new JComboBox<>(currencyName);
		toCurrCombo.setBounds(20,245,295,30);
		toCurrCombo.setSelectedIndex(0);
		toCurrCombo.setForeground(Color.black);
		toCurrCombo.setFont(new Font("Arial",Font.PLAIN,13));
		toCurrCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				toSymbol.setText((String)toCurrCombo.getSelectedItem());
				amountTextField.setText("");
				resultTextField.setText("");

			}
			
		});
		this.add(toCurrCombo);
		
		JLabel l6 = new JLabel("Amount");
		l6.setBounds(20,300,100,25);
		l6.setFont(new Font("Arial",Font.PLAIN,15));
		l6.setForeground(Color.BLACK);
		this.add(l6);
		
		amountTextField = new JTextField();
		amountTextField .setBounds(20,335,295,30);
		amountTextField .setFont(new Font("Arial",Font.BOLD,15));
		amountTextField .setForeground(Color.black);
		this.add(amountTextField );
		
		JLabel l7 = new JLabel("Result");
		l7.setBounds(20,445,100,25);
		l7.setFont(new Font("Arial",Font.PLAIN,15));
		l7.setForeground(Color.BLACK);
		this.add(l7);
		
		
		resultTextField = new JTextField();
		resultTextField.setBounds(20,480,295,30);
		resultTextField.setFont(new Font("Arial",Font.BOLD,15));
		resultTextField.setForeground(Color.black);
		this.add(resultTextField);
		
		JButton b = new JButton("Convert");
		b.setBounds(20,390,295,35);
		b.setFont(new Font("Arial",Font.BOLD,20));
		b.setForeground(Color.WHITE);
		b.setBorderPainted(false);
		b.setFocusable(false);
		b.setBackground(new Color(0,204,102));
		b.addActionListener(new  ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(amountTextField.getText() != null & amountTextField.getText() != "") {
					
					showResult();
				}
				
			}
		
			
		});
		this.add(b);
		
		fromSymbol = new JLabel(currencyName[0]);
		fromSymbol.setBounds(282,300,100,20);
		fromSymbol.setFont(new Font("Arial",Font.PLAIN,15));
		fromSymbol.setForeground(Color.BLACK);
		this.add(fromSymbol);
		
		toSymbol = new JLabel(currencyName[0]);
		toSymbol.setBounds(282,445,100,20);
		toSymbol.setFont(new Font("Arial",Font.PLAIN,15));
		toSymbol.setForeground(Color.BLACK);
		this.add(toSymbol);
	
	}
	private void showResult() {
		
		String from = (String) fromCurrCombo.getSelectedItem();
		String to = (String) toCurrCombo.getSelectedItem();
		double amount = Double.parseDouble(amountTextField.getText());
		
		
		double convertedAmount = CurrencyConverter.getConvertedAmount(from,to,amount);
		
		String result = String.format("%.2f",convertedAmount);
		
		resultTextField.setText(result);
		
		
	}
	
}
