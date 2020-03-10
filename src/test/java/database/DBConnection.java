package database;

import models.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private void setupConnection() {
        try {
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/qa02?"
                            + "user=root&password=rootroot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> selectFromTable(String tableName) throws Exception {
        try {
            setupConnection();

            preparedStatement = connect.prepareStatement(String.format("select * from %s", tableName));

            resultSet = preparedStatement
                    .executeQuery();
            return getGroupList(resultSet);
        } finally {
            close();
        }
    }

    public List<Group> selectFromTable(String tableName, int id) throws Exception {
        try {
            setupConnection();

            preparedStatement = connect.prepareStatement(String.format("SELECT * FROM %s WHERE id = ?", tableName));

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement
                    .executeQuery();
            return getGroupList(resultSet);
        } finally {
            close();
        }
    }

    public void insertIntoTable(String tableName, Group group) throws Exception {
        try {
            setupConnection();

            preparedStatement = connect.prepareStatement(
                    String.format("INSERT INTO QA02.GROUP (name, start_date) VALUES (?, ?)", tableName));

            preparedStatement.setString(1, group.getName());
            preparedStatement.setDate(2, group.getStartDate());
            preparedStatement.execute();
        } finally {
            close();
        }
    }

    public void updateTable(String tableName, int id, Group group) throws Exception {
        try {
            setupConnection();

            preparedStatement = connect.prepareStatement(
                    String.format("UPDATE %s SET name = ?, start_date = ? WHERE id = ?", tableName));

            preparedStatement.setString(1, group.getName());
            preparedStatement.setDate(2, group.getStartDate());
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } finally {
            close();
        }
    }

    public void deleteFromTable(String tableName, int id) throws Exception {
        try {
            setupConnection();

            preparedStatement = connect.prepareStatement(String.format("DELETE FROM %s WHERE id = ?", tableName));

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            close();
        }
    }

    private List<Group> getGroupList(ResultSet resultSet) throws SQLException {
        List<Group> groups = new ArrayList<Group>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Date startDate = resultSet.getDate("start_date");
            groups.add(new Group(id, name, startDate));
        }
        return groups;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception ignored) {
        }
    }

}