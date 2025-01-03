package org.example.football_manager.command.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.CommandService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueNameValidationImpl implements ConstraintValidator<UniqueNameValidation, String> {
    private final CommandService commandService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name == null || name.trim().isEmpty() || name.equals("null")) {
            return false;
        }
        return !commandService.existsByName(name);
    }
}
