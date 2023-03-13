package com.geniescode.backend.dao;

import com.geniescode.backend.entities.AddressDetails;
import com.geniescode.backend.entities.User;
import com.geniescode.backend.entities.UserCredentials;
import com.geniescode.backend.entities.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> findById(int id);
    void saveUser(UserDetails user);
    void updateUser(User user);
    void deleteUserById(int id);
    List<User> findAll();
    User findByUsernameAndPassword(UserCredentials credentials);
    List<AddressDetails> findAllDistrict();
}
