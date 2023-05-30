package com.resport.employee.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import com.resport.employee.dictionaries.ResponseEnum;
import com.resport.employee.exceptions.ConnectionException;
import com.resport.employee.exceptions.CrudException;
import com.resport.employee.exceptions.NotFoundException;
import com.resport.employee.models.dto.EmployeeDto;
import com.resport.employee.models.dto.GenericResponse;
import com.resport.employee.repositories.EmployeeRepository;

@ApplicationScoped
public class EmployeeService {
    @Inject
    EmployeeRepository parameterRepository;

    public GenericResponse<List<EmployeeDto>> getAll()
            throws ConnectionException, SQLException, NotFoundException {
        GenericResponse<List<EmployeeDto>> response = new GenericResponse<>();
        response.initValues(ResponseEnum.OK);
        response.setData(this.parameterRepository.getAll().orElseThrow(() -> new NotFoundException()));
        return response;
    }

    public GenericResponse<List<EmployeeDto>> getBySearch(String searchType, String search)
            throws ConnectionException, SQLException, NotFoundException {
        GenericResponse<List<EmployeeDto>> response = new GenericResponse<>();
        response.initValues(ResponseEnum.OK);
        response.setData(
                this.parameterRepository.getBySearch(searchType, search).orElseThrow(() -> new NotFoundException()));
        return response;
    }

    public GenericResponse<Void> create(@Valid EmployeeDto employeeDto)
            throws ConnectionException, SQLException, CrudException, ParseException, NotFoundException {
        GenericResponse<Void> response = new GenericResponse<Void>();
        response.initValues(ResponseEnum.OK);
        this.parameterRepository.create(employeeDto);
        return response;
    }

    public GenericResponse<Void> update(String dni, @Valid EmployeeDto employee)
            throws ConnectionException, SQLException, CrudException, NotFoundException, ParseException {
        GenericResponse<Void> response = new GenericResponse<Void>();
        response.initValues(ResponseEnum.OK);
        this.parameterRepository.update(dni, employee);
        return response;
    }

    public GenericResponse<Void> delete(String dni)
            throws ConnectionException, SQLException, CrudException, NotFoundException {
        GenericResponse<Void> response = new GenericResponse<Void>();
        response.initValues(ResponseEnum.OK);
        this.parameterRepository.deleteEmployee(dni);
        return response;
    }
}