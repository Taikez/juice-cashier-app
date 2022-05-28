package com.taikez;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    DatabaseConnection db = new DatabaseConnection();
    User loggedUser = null;
    String phone;
    JLabel titleLabel, phoneLabel, passwordLabel;
    JTextField phoneTextField;
    JPasswordField passwordPasswordField;
    JButton loginButton, backButton;

    public Login() {
        frameSettings();

        titleLabel = new JLabel("Login");
        titleLabel.setBounds(152,15,200,50);
        titleLabel.setFont(titleLabel.getFont().deriveFont(25f));
        add(titleLabel);

        phoneLabel = new JLabel("Phone :");
        phoneLabel.setBounds(25,80, 50, 20);
        add(phoneLabel);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(150,80, 200,20);
        add(phoneTextField);

        passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(25, 120, 100, 20);
        add(passwordLabel);

        passwordPasswordField = new JPasswordField();
        passwordPasswordField.setBounds(150,120,200,20);
        add(passwordPasswordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(145, 200, 150, 30);
        loginButton.setFont(loginButton.getFont().deriveFont(18f));
        loginButton.setBackground(Color.decode("#82E480"));
        loginButton.addActionListener(this);
        add(loginButton);

        backButton = new JButton("Back");
        backButton.setBounds(145,250,150,30);
        backButton.setFont(backButton.getFont().deriveFont(18f));
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

    public boolean validInput(User user) {
        boolean inputIsValid = true;
        String message = "";
        if(user.getPhoneNumber().isEmpty()) {
            inputIsValid = false;
            message = "Phone is empty!";
        } else if(user.getPassword().isEmpty()) {
            inputIsValid = false;
            message = "Password is empty!";
        }

        if(inputIsValid == false)
            JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);

        return inputIsValid;
    }

    public boolean validateUser(User user) throws SQLException {
        try {
            ResultSet rs = db.getUserData(user.getPhoneNumber());
            while(rs.next()) {
               String phoneNumber = rs.getString("user_phone");
               String password = rs.getString("user_password");

               if(user.getPassword().equals(password))
                   return true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            this.dispose();

            phone = phoneTextField.getText();
            String password = passwordPasswordField.getText();
            User checkUser = new User(null, phone, password, null);
        
            if(validInput(checkUser) == true) {
                try {
                    if(validateUser(checkUser)) {
                        String message = "Login success! Press 'OK' to continue!";
                        JOptionPane.showMessageDialog(this, message, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new TokoBuah(phone);
                    } else {
                        JOptionPane.showMessageDialog(this, "Credential Error!", "ERROR", JOptionPane.ERROR_MESSAGE);
                        new Login();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                new Login();
            }
        } else {
            this.dispose();
            new Home();
        }
    }
}
