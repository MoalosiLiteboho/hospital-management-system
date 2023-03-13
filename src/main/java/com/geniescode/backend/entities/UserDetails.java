package com.geniescode.backend.entities;

import java.util.Date;

public record UserDetails(
        String name,
        String surname,
        Date dateOfBirth,
        String gender,
        String authority,
        String email,
        String district
) {}
