package com.geniescode.backend.controllers;

import com.geniescode.backend.entities.UserCredentials;
import com.geniescode.backend.services.SignInService;
import com.geniescode.frondend.logIn.SignIn;
import com.geniescode.frondend.registration.SignUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInController  implements ActionListener {
    private final SignIn signIn;

    public SignInController(SignIn signIn) {
        this.signIn = signIn;
        this.signIn.addButtonListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == signIn.getLogIn())
            signInAction();
        if (event.getSource() == signIn.getRegistration())
            registrationAction();
    }

    private void signInAction() {
        new SignInService(
                new UserCredentials(
                        signIn.getEmail(),
                        signIn.getPassword()),
                signIn);
    }

    private void registrationAction() {
        new SignUp().setVisible(true);
        signIn.dispose();
    }
}
