package com.taikez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    JLabel titleLabel;
    JButton registerButton, loginButton, exitButton;

    public Home() {
        frameSettings();

        titleLabel = new JLabel("Toko Jus Buah");
        titleLabel.setBounds(100,15,200,50);
        titleLabel.setFont(titleLabel.getFont().deriveFont(25f));
        add(titleLabel);

        registerButton = new JButton("Register");
        registerButton.setBounds(110,115,150,30);
        registerButton.setFont(registerButton.getFont().deriveFont(18f));
        registerButton.setBackground(Color.decode("#F2CEE8"));
        registerButton.addActionListener(this);
        add(registerButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(110, 165, 150, 30);
        loginButton.setFont(loginButton.getFont().deriveFont(18f));
        loginButton.setBackground(Color.decode("#82E480"));
        loginButton.addActionListener(this);
        add(loginButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(110,300,150,30);
        exitButton.setFont(exitButton.getFont().deriveFont(18f));
        exitButton.setBackground(Color.decode("#FF7B73"));
        exitButton.addActionListener(this);
        add(exitButton);

        setVisible(true);
    }

    public void frameSettings() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#C8FFC7"));
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400,400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registerButton) {
            this.dispose();
            new Register();
        } else if(e.getSource() == loginButton) {
            this.dispose();
            new Login();
        } else {
            JOptionPane.showMessageDialog(this, "See You Later", "EXIT", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }
}
