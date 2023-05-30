package com.resport.employee.exceptions.Handler;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.resport.employee.dictionaries.ResponseEnum;
import com.resport.employee.models.dto.ValidErrorResponse;

@Provider
public class ValidExceptionsHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ValidErrorResponse messageError = new ValidErrorResponse();
        messageError.setHttpCode(ResponseEnum.NOPASSVALID.getResponsehttp());
        messageError.setCodeError(ResponseEnum.NOPASSVALID.getCodeError());
        messageError.setDescriptionError(ResponseEnum.NOPASSVALID.getMessageSystem());
        e.getConstraintViolations().iterator().forEachRemaining(s -> {
            messageError.getReasons().add(s.getMessageTemplate());
        });
        return Response.status(messageError.getHttpCode()).entity(messageError).build();
    }
}