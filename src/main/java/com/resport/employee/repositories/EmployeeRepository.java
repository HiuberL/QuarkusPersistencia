package com.resport.employee.repositories;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.resport.employee.dictionaries.ResponseEnum;
import com.resport.employee.exceptions.ConnectionException;
import com.resport.employee.exceptions.CrudException;
import com.resport.employee.exceptions.NotFoundException;
import com.resport.employee.models.dao.Employee;
import com.resport.employee.models.dto.EmployeeDto;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Dependent
public class EmployeeRepository implements PanacheRepository<Employee> {

        public Optional<List<EmployeeDto>> getAll()
                        throws ConnectionException, SQLException {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                mapper.setDateFormat(dateFormat);
                return Optional.of(mapper.convertValue(findAll().list(),
                                new TypeReference<List<EmployeeDto>>() {
                                }));
        }

        public Optional<List<EmployeeDto>> getBySearch(String searchType, String argument)
                        throws ConnectionException, SQLException {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                mapper.setDateFormat(dateFormat);
                return Optional.of(
                                mapper.convertValue(this.find(searchType, argument).list(),
                                                new TypeReference<List<EmployeeDto>>() {
                                                }));
        }

        public void create(EmployeeDto employee)
                        throws ConnectionException, SQLException, CrudException, ParseException, NotFoundException {
                Employee createEmployee = new Employee(employee);
                if (Optional.ofNullable(this.find("dni", createEmployee.getDni()).firstResult()).isPresent())
                        throw new CrudException(ResponseEnum.BADREQUEST, "El elemento ya existe");
                createEmployee.persist();
                if (!createEmployee.isPersistent())
                        throw new CrudException(ResponseEnum.NOTCONNECTION, "No se pudo guardar la información");

        }

        public void update(String dni, EmployeeDto employee)
                        throws ConnectionException, SQLException, NotFoundException, CrudException, ParseException {
                Employee updateEmployee = (Employee) Optional.ofNullable(this.find("dni", dni).firstResult())
                                .orElseThrow(() -> new NotFoundException());
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                updateEmployee.setName(employee.getName());
                updateEmployee.setLastName(employee.getLastName());
                updateEmployee.setPosition(employee.getPosition());
                updateEmployee.setBirthDate(dateFormat.parse(employee.getBirthDate()));
                updateEmployee.setIngressDate(dateFormat.parse(employee.getIngressDate()));
                updateEmployee.persist();
                if (!updateEmployee.isPersistent())
                        throw new CrudException(ResponseEnum.NOTCONNECTION, "No se pudo guardar la información");

        }

        public void deleteEmployee(String dni)
                        throws ConnectionException, SQLException, NotFoundException, CrudException {
                Employee deleteEmployee = (Employee) Optional.ofNullable(this.find("dni", dni).firstResult())
                                .orElseThrow(() -> new NotFoundException());
                deleteEmployee.delete();
                if (!deleteEmployee.isPersistent())
                        throw new CrudException(ResponseEnum.NOTCONNECTION, "No se pudo eliminar la información");
        }
}
