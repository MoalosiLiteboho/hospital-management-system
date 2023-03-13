package com.geniescode.backend.dao;

import com.geniescode.backend.entities.AddressDetails;
import com.geniescode.backend.entities.User;
import com.geniescode.backend.entities.UserCredentials;
import com.geniescode.backend.entities.UserDetails;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.geniescode.backend.db.DatabaseConnection.connection;

public class UserDAO implements IUserDAO{
    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void saveUser(UserDetails user) {
        try {
            PreparedStatement statement = connection.get().prepareStatement("insert into HospitalSystemManagement.UserTable(Name, Surname, DateOfBirth, Gender, Email) " +
                    "value (? , ?, ?, ?, ?) ");
            statement.setString(1, user.email());
            statement.setString(2, user.surname());
            statement.setDate(3, (Date) user.dateOfBirth());
            statement.setString(4, user.gender());
            statement.setString(5, user.email());

            if(statement.executeUpdate() != 0){
                statement = connection.get().prepareStatement("insert into HospitalSystemManagement.UserAccountsTable(UserId, Authority, Username, Password, Enabled, ExpiryDate) " +
                        "values (?, ?, ?, ?, ?, ?)");
                statement.setInt(1, 1);
                statement.setInt(2, 4);
                statement.setString(3, user.name() + "." + user.surname());
                statement.setString(4, user.surname() + "@12345");
                statement.setBoolean(5, true);
                statement.setDate(6, getAccountExpiryDate());

                if(statement.executeUpdate() != 0) {
                    statement = connection.get().prepareStatement("insert into HospitalSystemManagement.AddressTable(UserId, Code) values (?, ?)");
                    statement.setInt(1, 1);
                    statement.setInt(2, 100);

                    statement.executeUpdate();
                }
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByUsernameAndPassword(UserCredentials credentials) {
        return null;
    }

    @Override
    public List<AddressDetails> findAllDistrict() {
        List<AddressDetails> list = new ArrayList<>(List.of());
        try {
            PreparedStatement statement = connection.get().prepareStatement("select * from HospitalSystemManagement.DistrictsTable");
            ResultSet result = statement.executeQuery();

            while(result.next())
                list.add(new AddressDetails(
                        result.getInt("Code"),
                        result.getString("Name"))
                );
            return list;
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    private Date getAccountExpiryDate() {
        return null;
    }
}
