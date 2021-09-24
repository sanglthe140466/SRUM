package com.srum.util.validator;

import com.srum.dto.ClazzDTO;
import com.srum.util.annotation.ValidDateRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 02/09/2021
 */
public class DateIntervalValidator implements ConstraintValidator<ValidDateRange, ClazzDTO> {

    @Override
    public boolean isValid(ClazzDTO value, ConstraintValidatorContext context) {
        if (value.getOpenDate() == null || value.getEndDate() == null){
            return false;
        }
        return value.getOpenDate().before(value.getEndDate());
    }
}
