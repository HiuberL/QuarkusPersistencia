package com.resport.employee.exceptions.Handler;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.resport.employee.dictionaries.ResponseEnum;
import com.resport.employee.exceptions.ConnectionException;
import com.resport.employee.models.dto.GenericResponse;

@Provider
public class ConnectionExceptionHandler implements ExceptionMapper<ConnectionException> {
    @Inject
    Logger log;

    @Override
    public Response toResponse(ConnectionException e) {
        GenericResponse<String> response = new GenericResponse<String>();
        response.initValues(ResponseEnum.NOTCONNECTION);
        response.setDescriptionError(e.getMessageSystem());
        log.error(e);
        return Response.status(response.getHttpCode()).entity(response).build();
    }

}