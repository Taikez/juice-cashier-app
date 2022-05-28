package com.taikez;

import java.sql.*;

public class DatabaseConnection {
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    public ResultSetMetaData resultSetMetaData;
    public PreparedStatement preparedStatement;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tokojusbuah", "root", "");
            statement = connection.createStatement();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet execQuery(String query) {
        try {
            resultSet = statement.executeQuery(query);
            resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getUsersData() {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user");

            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getUserData(String phoneNumber) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_phone = ?");

            preparedStatement.setString(1, phoneNumber);
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void updateStock(int newStock, int selectedID) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE juice SET juice_stock = ? WHERE juice_id = ?");
            preparedStatement.setInt(1, newStock);
            preparedStatement.setInt(2, selectedID);

            preparedStatement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public String getUserID(String phone) {
    	String userIDResult = null;
    	try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE `user_phone` = ?");
			preparedStatement.setString(1, phone);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				userIDResult = resultSet.getString("user_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return userIDResult;
    }
    
    public String getJuiceID(String name) {
    	String juiceIDResult = null;
    	try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `juice` WHERE `juice_name` = ?");
			preparedStatement.setString(1, name);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				juiceIDResult = resultSet.getString("juice_id");
			}
			
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return juiceIDResult;
    }
    
    public ResultSet getJuice() {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM juice");

            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void insertNewUser(User user) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `user` (`user_name`, `user_phone`, `user_password`)" + "VALUES (?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertTransaction(String userID, String juiceID) {
    	try {
			preparedStatement = connection.prepareStatement("INSERT INTO `transaction` (`transaction_date`, `user_id`, `juice_id`, `quantity`)" + " VALUES (NOW(), ?, ?, 1)");
			preparedStatement.setString(1, userID);
			preparedStatement.setString(2, juiceID);
			
			preparedStatement.execute();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
