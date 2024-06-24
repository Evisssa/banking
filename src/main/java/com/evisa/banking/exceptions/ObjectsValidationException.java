package com.evisa.banking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

//@AllArgsConstructor
@RequiredArgsConstructor
public class ObjectsValidationException extends RuntimeException{

    @Getter
    private final Set<String> violations;
    @Getter
    private final String violationSource;

}
