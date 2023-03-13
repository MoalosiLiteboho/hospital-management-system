package com.geniescode.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Supplier;

public interface DatabaseConnection extends Supplier<Connection> {

    DatabaseConnection connection = () -> {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalSystemManagement", "root", "");
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    };
}
