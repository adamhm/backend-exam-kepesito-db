package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
    public List<String> checkOverpopulation() {
        List<String> breeds = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT breed FROM dinosaur WHERE actual > expected ORDER BY breed;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                breeds.add(resultSet.getString("breed"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        
        return breeds;
    }
}
