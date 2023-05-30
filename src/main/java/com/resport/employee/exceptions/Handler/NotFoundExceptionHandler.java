package com.resport.employee.exceptions.Handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.resport.employee.dictionaries.ResponseEnum;
import com.resport.employee.exceptions.NotFoundException;
import com.resport.employee.models.dto.GenericResponse;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        GenericResponse<String> response = new GenericResponse<String>();
        response.initValues(ResponseEnum.NOTFOUND);
        response.setDescriptionError(e.getMessageSystem());
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}