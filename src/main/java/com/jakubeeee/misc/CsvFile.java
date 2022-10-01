package com.jakubeeee.misc;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = CsvValidator.class)
public @interface CsvFile {
    String message() default "Invalid file extension";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
