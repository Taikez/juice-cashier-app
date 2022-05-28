package com.taikez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class TokoBuah extends JFrame {
    DatabaseConnection db = new DatabaseConnection();
    String phone;
    Vector<Integer> juiceIDVector = new Vector<>();
    Vector<String> juiceNameVector = new Vector<>();
    Vector<Integer> juicePriceVector = new Vector<>();
    Vector<Integer> juiceStockVector = new Vector<>();

    JLabel titleLabel, juiceLabel, priceLabel, stockLabel;
    JComboBox juiceComboBox;
    JTextField priceTextField, stockTextField;
    JButton buyButton, backButton, addStockButton;

    public void setData() {
        try {
            ResultSet rs = db.getJuice();
            while (rs.next()) {
                Juice juice = new Juice(rs.getInt("juice_id"), rs.getString("juice_name"), rs.getInt("juice_price"), rs.getInt("juice_stock"));
                juiceIDVector.add(juice.getJuiceID());
                juiceNameVector.add(juice.getJuiceName());
                juicePriceVector.add(juice.getJuicePrice());
                juiceStockVector.add(juice.getJuiceStock());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNewStock(int newStock, int ID) {
        try {
            db.updateStock(newStock, ID);     
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
    
    public TokoBuah(String phone) {
    	this.phone = phone;
        setData();
        frameSettings();

        titleLabel = new JLabel("Transaction");
        titleLabel.setBounds(131,15,200,50);
        titleLabel.setFont(titleLabel.getFont().deriveFont(25f));
        add(titleLabel);

        juiceLabel = new JLabel("Choose Product: ");
        juiceLabel.setBounds(15, 75, 100, 20);
        add(juiceLabel);

        juiceComboBox = new JComboBox(juiceNameVector);
        juiceComboBox.setBounds(117, 75, 213, 20);
        juiceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                priceTextField.setText(String.valueOf(juicePriceVector.get(juiceComboBox.getSelectedIndex())));
                stockTextField.setText(String.valueOf(juiceStockVector.get(juiceComboBox.getSelectedIndex())));
            }
        });

        add(juiceComboBox);

        priceLabel = new JLabel("Price: ");
        priceLabel.setBounds(15, 107, 100, 20);
        add(priceLabel);

        priceTextField = new JTextField();
        priceTextField.setBounds(117, 107, 213, 20);
        priceTextField.setEditable(false);
        add(priceTextField);

        stockLabel = new JLabel("Stock: ");
        stockLabel.setBounds(15, 139, 100, 20);
        add(stockLabel);

        stockTextField = new JTextField();
        stockTextField.setBounds(117,139,213,20);
        stockTextField.setEditable(false);
        add(stockTextField);

        buyButton = new JButton("Buy");
        buyButton.setBounds(135, 200, 150, 30);
        buyButton.setFont(buyButton.getFont().deriveFont(18f));
        buyButton.setBackground(Color.decode("#E4CE81"));

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String juiceID = db.getJuiceID(juiceComboBox.getSelectedItem().toString());
            	String userID = db.getUserID(phone);
            	
                setNewStock(juiceStockVector.get(juiceComboBox.getSelectedIndex()) - 1, juiceIDVector.get(juiceComboBox.getSelectedIndex()));
                db.insertTransaction(userID, juiceID);
                JOptionPane.showMessageDialog(null, "Transaction Success!", "SUCCESSS", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new TokoBuah(phone);
            }
        });
        add(buyButton);

        backButton = new JButton("Back");
        backButton.setBounds(135,250,150,30);
        backButton.setFont(backButton.getFont().deriveFont(18f));
        backButton.setBackground(Color.decode("#E4CE80"));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        add(backButton);

        setVisible(true);

    }

    private void frameSettings() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#C8FFC7"));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400,400);
    }
}

