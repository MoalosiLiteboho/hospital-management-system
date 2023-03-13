package com.geniescode.backend.entities;

import java.util.Date;

public record User(Integer Id, String name, String surname, Date dateOfBirth, String gender, String username, String password, String authority, String email, Boolean enabled, Date expiryDate) {}
