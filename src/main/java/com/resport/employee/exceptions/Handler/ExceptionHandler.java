package com.resport.employee.exceptions.Handler;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.resport.employee.dictionaries.ResponseEnum;
import com.resport.employee.models.dto.GenericResponse;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Inject
    Logger log;

    @Override
    public Response toResponse(Exception e) {
        GenericResponse<String> response = new GenericResponse<String>();
        response.initValues(ResponseEnum.NOTEXPECT);
        String problem = "";
        for (StackTraceElement msg : e.getStackTrace()) {
            problem = problem + String.valueOf(msg) + " ";
        }
        response.setDescriptionError(
                "Problem:" + problem);
        log.error(e.getCause(), e);
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}
