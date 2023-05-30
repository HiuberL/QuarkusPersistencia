package com.resport.employee.exceptions.Handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.resport.employee.dictionaries.ResponseEnum;
import com.resport.employee.exceptions.CrudException;
import com.resport.employee.models.dto.GenericResponse;

@Provider
public class CrudExceptionHandler implements ExceptionMapper<CrudException> {

    @Override
    public Response toResponse(CrudException e) {
        GenericResponse<String> response = new GenericResponse<String>(ResponseEnum.BADREQUEST);
        response.setDescriptionError(e.getDescriptionError());
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}