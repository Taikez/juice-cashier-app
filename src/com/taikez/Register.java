package com.taikez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame implements ActionListener {
    DatabaseConnection db = new DatabaseConnection();

    JLabel titleLabel, nameLabel, phoneLabel, passwordLabel, confirmPasswordLabel;
    JTextField nameTextField, phoneTextField;
    JPasswordField passwordPasswordField, confirmPasswordPasswordField;
    JButton registerButton, loginButton, backButton, resetButton;

    public Register() {
        frameSettings();

        titleLabel = new JLabel("Register");
        titleLabel.setBounds(152,15,200,50);
        titleLabel.setFont(titleLabel.getFont().deriveFont(25f));
        add(titleLabel);

        nameLabel = new JLabel("Name :");
        nameLabel.setBounds(25,80, 50, 20);
        add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(150,80, 200,20);
        add(nameTextField);

        phoneLabel = new JLabel("Phone :");
        phoneLabel.setBounds(25, 120, 50, 20);
        add(phoneLabel);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(150,120,200,20);
        add(phoneTextField);

        passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(25, 160, 100, 20);
        add(passwordLabel);

        passwordPasswordField = new JPasswordField();
        passwordPasswordField.setBounds(150, 160, 200, 20);
        add(passwordPasswordField);

        confirmPasswordLabel = new JLabel("Confirm Password :");
        confirmPasswordLabel.setBounds(25, 200, 130, 20);
        add(confirmPasswordLabel);

        confirmPasswordPasswordField = new JPasswordField();
        confirmPasswordPasswordField.setBounds(150,200,200,20);
        add(confirmPasswordPasswordField);

        resetButton = new JButton("Reset");
        resetButton.setBounds(30, 300, 100, 30);
        resetButton.setFont(resetButton.getFont().deriveFont(12f));
        resetButton.setBackground(Color.decode("#C7A7F0"));
        resetButton.addActionListener(this);
        add(resetButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(30, 250, 325,30);
        registerButton.setFont(registerButton.getFont().deriveFont(12f));
        registerButton.setBackground(Color.decode("#F2CEE8"));
        registerButton.addActionListener(this);
        add(registerButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(142,300,100,30);
        loginButton.setFont(loginButton.getFont().deriveFont(12f));
        loginButton.setBackground(Color.decode("#82E480"));
        loginButton.addActionListener(this);
        add(loginButton);

        backButton = new JButton("Back");
        backButton.setBounds(254, 300, 100, 30);
        backButton.setFont(backButton.getFont().deriveFont(12f));
        backButton.setBackground(Color.decode("#E4CE80"));
        backButton.addActionListener(this);
        add(backButton);

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
            User newUser = new User(nameTextField.getText(), phoneTextField.getText(), passwordPasswordField.getText(), confirmPasswordPasswordField.getText());
            if(validUser(newUser) == true) {
                db.insertNewUser(newUser);
                JOptionPane.showMessageDialog(this, "Successfully Registered", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(e.getSource() == resetButton) {
            this.dispose();
            new Register();
        } else if(e.getSource() == loginButton) {
            this.dispose();
            new Login();
        } else {
            this.dispose();
            new Main();
        }
    }

    public boolean validUser(User user) {
        boolean isAllValid = true;
        String message = "";

        if(user.getName().isEmpty()) {
            isAllValid = false;
            message = "Name Empty!";
        } else if(user.getPhoneNumber().isEmpty()) {
            isAllValid = false;
            message = "Phone Number Empty!";
        } else if(user.getPassword().isEmpty()) {
            isAllValid = false;
            message = "Password Empty!";
        } else if (user.getConfirmPassword().isEmpty()) {
            isAllValid = false;
            message = "Confirm Password Empty";
        } else if(!(user.getConfirmPassword().equals(user.getPassword()))) {
            isAllValid = false;
            message = "Confirm Password Invalid";
        }

        if(isAllValid == false) {
            JOptionPane.showMessageDialog(this, message, "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        return isAllValid;
    }
}
