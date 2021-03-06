package com.balinasoft.firsttask.system.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InDateRangeValidator implements ConstraintValidator<InDateRange, java.util.Date> {

    private static final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

    private InDateRange constraintAnnotation;

    @Override
    public void initialize(InDateRange constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(java.util.Date value, ConstraintValidatorContext context) {
        try {
            final Date min = dateParser.parse(constraintAnnotation.min());
            final Date max = dateParser.parse(constraintAnnotation.max());
            return value == null ||
                    (value.after(min) && value.before(max));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}