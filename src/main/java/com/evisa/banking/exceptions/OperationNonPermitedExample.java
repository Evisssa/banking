package com.evisa.banking.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OperationNonPermitedExample extends RuntimeException{

    private final String errorMsg;
    private final String operationId;
    private final String source;
    private final String dependency;

}
