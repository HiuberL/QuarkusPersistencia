package com.resport.employee.exceptions;

import javax.ws.rs.core.Response.Status;

import com.resport.employee.dictionaries.ResponseEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CrudException extends Exception {
    private int codeError;
    private String descriptionError;
    private Status responsehttp;

    public CrudException(ResponseEnum response) {
        this.codeError = response.getCodeError();
        this.descriptionError = response.getMessageSystem();
        this.responsehttp = response.getResponsehttp();
    }

    public CrudException(ResponseEnum response, Exception e) {
        this.codeError = response.getCodeError();
        this.descriptionError = response.getMessageSystem();
        this.responsehttp = response.getResponsehttp();
    }

    public CrudException(ResponseEnum response, String message) {
        this.codeError = response.getCodeError();
        this.descriptionError = message;
        this.responsehttp = response.getResponsehttp();
    }
}
