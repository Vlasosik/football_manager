package org.example.football_manager.command.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = UniqueNameValidationImpl.class)
public @interface UniqueNameValidation {
    String message() default "Bad request!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
