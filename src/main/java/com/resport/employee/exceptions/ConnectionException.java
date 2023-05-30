package com.resport.employee.exceptions;

import java.sql.SQLException;

import javax.ws.rs.core.Response.Status;

import com.resport.employee.dictionaries.ResponseEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionException extends SQLException {
    private int codeError;
    private String messageUser;
    private String messageSystem;
    private Status responsehttp;

    public ConnectionException(ResponseEnum response) {
        this.codeError = response.getCodeError();
        this.messageSystem = response.getMessageSystem();
        this.responsehttp = response.getResponsehttp();
    }

    public ConnectionException(ResponseEnum response, Exception e) {
        this.codeError = response.getCodeError();
        this.messageSystem = this.getClass().getCanonicalName() + e.getMessage();
        this.responsehttp = response.getResponsehttp();
    }
}
