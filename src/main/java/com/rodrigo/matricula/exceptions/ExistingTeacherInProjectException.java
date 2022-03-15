package com.rodrigo.matricula.exceptions;

public class ExistingTeacherInProjectException extends Exception{
    public ExistingTeacherInProjectException(String message) {
        super(message);
    }
}
