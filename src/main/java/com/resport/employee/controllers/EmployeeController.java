package com.resport.employee.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.resport.employee.dictionaries.SearchTypeEnum;
import com.resport.employee.exceptions.ConnectionException;
import com.resport.employee.exceptions.CrudException;
import com.resport.employee.exceptions.NotFoundException;
import com.resport.employee.models.dto.EmployeeDto;
import com.resport.employee.models.dto.GenericResponse;
import com.resport.employee.services.EmployeeService;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/empleados")
public class EmployeeController {
        @Inject
        EmployeeService parameterService;

        @GET
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Operation(summary = "Empleados", description = "Obtener listado de empleados")
        @APIResponses(value = {
                        @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
        })
        public Response getAll() throws ConstraintViolationException, ConnectionException, NotFoundException,
                        SQLException, CrudException {
                return Response.ok().entity(this.parameterService.getAll()).build();
        }

        @GET
        @Path("d")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Operation(summary = "Empleados", description = "Obtener listado de empleados por criterio de búqueda")
        @APIResponses(value = {
                        @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
        })
        public Response getBySearch(@QueryParam("type") SearchTypeEnum searchType, @QueryParam("search") String search)
                        throws ConstraintViolationException, ConnectionException, NotFoundException,
                        SQLException, CrudException {
                return Response.ok().entity(this.parameterService.getBySearch(searchType.getName(), search)).build();
        }

        @POST
        @Transactional
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Operation(summary = "Empleados", description = "Crea un empleado")
        @APIResponses(value = {
                        @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
        })
        public Response create(@RequestBody EmployeeDto request)
                        throws ConstraintViolationException, ConnectionException, NotFoundException, SQLException,
                        CrudException, ParseException {
                return Response.ok().entity(this.parameterService.create(request)).build();
        }

        @PUT
        @Path("{dni}")
        @Transactional
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Operation(summary = "Empleados", description = "Actualiza un empleado")
        @APIResponses(value = {
                        @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
        })
        public Response update(@PathParam("dni") String dni, @RequestBody EmployeeDto request)
                        throws ConstraintViolationException, ConnectionException, NotFoundException, SQLException,
                        CrudException, ParseException {
                return Response.ok().entity(this.parameterService.update(dni, request)).build();
        }

        @DELETE
        @Transactional
        @Path("{dni}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Operation(summary = "Empleados", description = "Elimina un empleado")
        @APIResponses(value = {
                        @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "503", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "501", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
                        @APIResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class)))
        })
        public Response delete(@PathParam("dni") String dni)
                        throws ConstraintViolationException, ConnectionException, NotFoundException, SQLException,
                        CrudException {
                return Response.ok().entity(this.parameterService.delete(dni)).build();
        }
}