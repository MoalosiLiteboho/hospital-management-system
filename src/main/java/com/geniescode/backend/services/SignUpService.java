package com.geniescode.backend.services;

import com.geniescode.backend.dao.UserDAO;
import com.geniescode.backend.entities.AddressDetails;
import com.geniescode.backend.entities.UserDetails;
import com.geniescode.frondend.registration.SignUp;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.geniescode.backend.validations.SignUpValidator.isAddressValid;
import static com.geniescode.backend.validations.SignUpValidator.isAuthorityValid;
import static com.geniescode.backend.validations.SignUpValidator.isDateOfBirthValid;
import static com.geniescode.backend.validations.SignUpValidator.isEmailValid;
import static com.geniescode.backend.validations.SignUpValidator.isGenderValid;
import static com.geniescode.backend.validations.SignUpValidator.isNameValid;
import static com.geniescode.backend.validations.SignUpValidator.isSurnameValid;

public class SignUpService {
    private final SignUp registration;
    private final UserDetails user;

    public SignUpService(SignUp registration, UserDetails user) {
        this.registration = registration;
        this.user = user;
        registrationProcess();
    }

    public static List<String> getDistrictList() {
        return new UserDAO().findAllDistrict()
                .stream()
                .map(AddressDetails::districtName)
                .collect(Collectors.toList());
    }


    private void registrationProcess() {
        String result = isNameValid
                .and(isSurnameValid)
                .and(isDateOfBirthValid)
                .and(isGenderValid)
                .and(isAuthorityValid)
                .and(isEmailValid)
                .and(isAddressValid)
                .apply(user);

        registerUserIfAllFieldAreFilled(result, displayMessage);
    }

    private void registerUserIfAllFieldAreFilled(String result, Consumer<String> showUserError) {
        if(!"SUCCESS".equals(result)) {
            showUserError.accept(result);
            return;
        }
        new UserDAO().saveUser(user);
        displayMessage.accept(user.name() + " " + user.surname() + " has registered successfully");
        clearFields.accept(registration);
    }

    private final Consumer<SignUp> clearFields = registration -> {
        registration.clearName();
        registration.clearSurname();
        registration.clearDateOfBirth();
        registration.clearGender();
        registration.clearEmail();
        registration.clearAddress();
    };
    private final Consumer<String> displayMessage = message -> JOptionPane.showMessageDialog(null, message);
}
