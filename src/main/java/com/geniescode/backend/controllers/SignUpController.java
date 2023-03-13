package com.geniescode.backend.controllers;

import com.geniescode.backend.entities.UserDetails;
import com.geniescode.backend.services.SignUpService;
import com.geniescode.frondend.logIn.SignIn;
import com.geniescode.frondend.registration.SignUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.geniescode.backend.services.SignUpService.getDistrictList;

public class SignUpController implements ActionListener {
    private final SignUp registration;

    public SignUpController(SignUp registration) {
        this.registration = registration;
        registration.addButtonListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == registration.getRegistration())
            registrationAction();
        if(event.getSource() == registration.getLogIn())
            logInAction();
    }

    private void registrationAction() {
        new SignUpService(
                registration,
                new UserDetails(
                        registration.getName(),
                        registration.getSurname(),
                        registration.getDateOfBirth(),
                        registration.getGender(),
                        registration.getAuthorities(),
                        registration.getEmail(),
                        registration.getDistrictName()
                )
        );
    }

    private void logInAction() {
        new SignIn().setVisible(true);
        registration.dispose();
    }

    public static List<String> getDistricts() {
        return getDistrictList();
    }
}
