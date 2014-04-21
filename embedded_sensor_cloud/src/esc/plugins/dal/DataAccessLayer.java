package esc.plugins.dal;

import esc.plugins.Contact;
import esc.plugins.ResultSetMapper;

import java.sql.*;
import java.util.List;

/**
 * @author Alex
 */
public class DataAccessLayer implements IDataAccessLayer{


    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String sql;

    public DataAccessLayer() {
        try {
            if (connection != null) connection.close();

            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://ALEX-NOTEBOOK;databaseName=ErpData";
            connection = DriverManager.getConnection(connectionUrl, "test_user", "password_yo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Contact> searchContacts(String text, boolean onlyActive) {
        sql = "SELECT * FROM contact WHERE name LIKE ? AND isActive = ?;";
        try {
            resultSet = null;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%"+text+"%");
            preparedStatement.setBoolean(2, onlyActive);

            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                ResultSetMapper<Contact> resultSetMapper = new ResultSetMapper<>();
                return resultSetMapper.mapToList(resultSet, Contact.class);
            }

        } catch (SQLException | ResultSetMapper.ResultSetMapperException e) {
            e.printStackTrace();
        }
        return null;
    }
}
