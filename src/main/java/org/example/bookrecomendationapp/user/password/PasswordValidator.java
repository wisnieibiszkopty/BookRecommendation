package org.example.bookrecomendationapp.user.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLowerCase = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigits = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = password.chars().anyMatch(ch -> "!@#$%^&*()_+[]{}|;':\",.<>?/".indexOf(ch) >= 0);
        boolean hasMinLength = password.length() >= 8;

        return hasUpperCase && hasLowerCase && hasDigits && hasSpecialChar && hasMinLength;
    }

}
