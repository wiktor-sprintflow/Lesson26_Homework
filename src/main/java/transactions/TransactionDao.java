package transactions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class TransactionDao {

    private static final String URL = "jdbc:mysql://localhost:3306/lesson26_homework?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    private Connection connection;

    public TransactionDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

     private void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void insert(Transaction transaction) {

        PreparedStatement insertStatement;

        try {
            String sql = "INSERT INTO transaction (type, description, amount, date) VALUES (?, ?, ?, ?)";
            insertStatement = this.connection.prepareStatement(sql);
            insertStatement.setString(1, String.valueOf(transaction.getType()));
            insertStatement.setString(2, transaction.getDescription());
            insertStatement.setDouble(3, transaction.getAmount());
            insertStatement.setString(4, String.valueOf(transaction.getDate()));
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Błąd przy zapisie do bazy: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Błąd danych");
        }
        close();
    }


    void deleteById(Long id) {

        PreparedStatement preparedStatement;
        try {
            String sql = "DELETE FROM transaction WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie udało się usunąć rekordu z bazy: " + e.getMessage());
        }
        close();
    }

    void update(Transaction transaction) {

        PreparedStatement updateStatement;
        try {
            String sql = "UPDATE transaction SET type = ? , description = ?, amount = ?, date = ? WHERE id = ?";
            updateStatement = this.connection.prepareStatement(sql);
            updateStatement.setString(1, String.valueOf(transaction.getType()));
            updateStatement.setString(2, transaction.getDescription());
            updateStatement.setDouble(3, transaction.getAmount());
            updateStatement.setString(4, String.valueOf(transaction.getDate()));
            updateStatement.setLong(5, transaction.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie udało się zaktualozować transakcji: " + e.getMessage());
        }
        close();
    }

    List<Transaction> findAll(Type transactionType) {

        List<Transaction> transactionsList = new ArrayList<>();

        PreparedStatement selectStatement;
        try {
            String sql = "SELECT * FROM transaction WHERE type = ?";
            selectStatement = connection.prepareStatement(sql);
            selectStatement.setString(1, String.valueOf(transactionType));
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Type type = Type.valueOf(resultSet.getString("type"));
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                Date date = Date.valueOf(resultSet.getString("date"));
                Transaction transaction = new Transaction(id, type, description, amount, date);
                transactionsList.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas odczytu: " + e.getMessage());
        }
        close();

        return transactionsList;
    }
}
