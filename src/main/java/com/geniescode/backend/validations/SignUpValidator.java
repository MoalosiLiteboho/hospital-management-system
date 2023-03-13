package com.geniescode.backend.validations;

import com.geniescode.backend.entities.UserDetails;

import java.util.function.Function;

public interface SignUpValidator extends Function<UserDetails, String> {
    SignUpValidator isNameValid = user -> !user.name().isEmpty() ?
            "SUCCESS" : "Name is Empty \n Please enter name";

    SignUpValidator isSurnameValid = user -> !user.surname().isEmpty() ?
            "SUCCESS" : "Surname is Empty \nPlease enter surname";

    SignUpValidator isDateOfBirthValid = user -> "SUCCESS";

    SignUpValidator isGenderValid = user -> !user.gender().isEmpty() ?
            "SUCCESS" : "gender not selected \nPlease selected gender";

    SignUpValidator isAuthorityValid = user -> !user.authority().isEmpty() ?
            "SUCCESS" : "Authority not selected \nPlease select authority";

    SignUpValidator isEmailValid = user -> {
        if (user.email().isEmpty())
            return  "Username is empty! \n Please enter email";
        if (!new EmailValidator().test(user.email()))
            return  "Email you entered is not in a CORRECT format \nPlease enter the email in a correct Format";
        return  "SUCCESS";
    };

    SignUpValidator isAddressValid = user -> !user.district().isEmpty() ?
            "SUCCESS" : "Address not selected \nPlease select Address";

    default SignUpValidator and (SignUpValidator others) {
        return user -> {
            String result = this.apply(user);
            return "SUCCESS".equals(result) ?
                    others.apply(user) : result;
        };
    }
}
