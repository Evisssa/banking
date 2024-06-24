package com.evisa.banking.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import com.evisa.banking.exceptions.ObjectsValidationException;
import static java.util.Arrays.stream;

public class ObjectsValidator<T extends Serializable> {


    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    public void validate(T objectToValidate) throws ObjectsValidationException {

        Set<ConstraintViolation<T>> violations= validator.validate(objectToValidate);
        if(!violations.isEmpty()){
              Set<String> errorMessages = violations.stream()
                      .map(ConstraintViolation::getMessage)
                      .collect(Collectors.toSet());
            throw new ObjectsValidationException(errorMessages,objectToValidate.getClass().getName());
        }

    }
}
