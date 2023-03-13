package com.geniescode.backend.services;

import com.geniescode.backend.dao.UserDAO;
import com.geniescode.backend.dto.UserCredentialsDTO;
import com.geniescode.backend.dtoMappers.UserCredentialDTOMapper;
import com.geniescode.backend.entities.UserCredentials;
import com.geniescode.frondend.dashboard.Dashboard;
import com.geniescode.frondend.logIn.SignIn;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.Optional;
import java.util.function.Consumer;

import static com.geniescode.backend.validations.SignInValidator.isPasswordValid;
import static com.geniescode.backend.validations.SignInValidator.isUsernameValid;

public class SignInService {
    private final UserCredentials credentials;
    private final UserDAO userDAO;
    private final SignIn logIn;

    public SignInService(UserCredentials credentials, SignIn logIn) {
        this.credentials = credentials;
        this.logIn = logIn;
        userDAO = new UserDAO();
        logInProcess();
    }

    public void logInProcess() {
        String result = isUsernameValid
                .and(isPasswordValid)
                .apply(credentials);

        tryToLogInIfAllFieldAreFilled(result, displayMessage);
    }

    private void tryToLogInIfAllFieldAreFilled(String result, Consumer<String> showUserError) {
        if(!"SUCCESS".equals(result)) showUserError.accept(result);
        else logIn(credentials);
    }

    private void logIn(UserCredentials credentials) {
        getUserByUsernameAndId(credentials)
                .ifPresentOrElse(
                        this::displayDashboard,
                        () -> JOptionPane.showMessageDialog(logIn, "Email and Password you entered are INVALID! \n Please try again using CORRECT credentials!")
                );
    }

    private Optional<UserCredentialsDTO> getUserByUsernameAndId(UserCredentials credentials) {
        return Optional.ofNullable(new UserCredentialDTOMapper()
                .apply(userDAO.findByUsernameAndPassword(credentials)));
    }

    private void displayDashboard(UserCredentialsDTO user) {
        new Dashboard(
                user.id(),
                user.username(),
                Color.green)
        .setVisible(true);
        logIn.dispose();
    }

    private final Consumer<String> displayMessage = message -> JOptionPane.showMessageDialog(null, message);
}
