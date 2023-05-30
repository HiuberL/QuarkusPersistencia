package com.resport.employee.models.dto;

import javax.json.bind.annotation.JsonbTransient;

import lombok.Getter;
import lombok.Setter;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.resport.employee.dictionaries.ResponseEnum;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class GenericResponse<T> {
    private Integer codeError;
    private String descriptionError;
    private T data;
    @JsonbTransient
    private Status httpCode;

    public GenericResponse() {

    }

    public GenericResponse(ResponseEnum responses) {
        this.codeError = responses.getCodeError();
        this.descriptionError = responses.getMessageSystem();
        this.httpCode = responses.getResponsehttp();
    }

    public void initValues(ResponseEnum responses) {
        this.codeError = responses.getCodeError();
        this.descriptionError = responses.getMessageSystem();
        this.httpCode = responses.getResponsehttp();
    }

}
