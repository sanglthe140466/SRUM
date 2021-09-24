package com.srum.util.annotation;

import com.srum.util.validator.DateIntervalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 02/09/2021
 */
@Documented
@Constraint(validatedBy = DateIntervalValidator.class)
@Target( {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {
    String message() default "End date must be after open date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
