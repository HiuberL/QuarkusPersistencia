package com.resport.employee.exceptions.Handler;

import java.sql.SQLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.resport.employee.dictionaries.ResponseEnum;
import com.resport.employee.models.dto.GenericResponse;

@Provider
public class SqlExceptionHandler implements ExceptionMapper<SQLException> {

    @Override
    public Response toResponse(SQLException e) {
        GenericResponse<String> response = new GenericResponse<String>(ResponseEnum.SQLERROR);
        response.setDescriptionError(e.getMessage());
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}