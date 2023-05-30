package com.resport.employee.models.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.resport.employee.models.dto.EmployeeDto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee extends PanacheEntity {

    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 60, nullable = false)
    private String lastName;
    @Column(length = 15, nullable = false)
    private String position;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private Date IngressDate;
    @Column(nullable = false)
    private String dni;

    public Employee(EmployeeDto employeeDto) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.name = employeeDto.getName();
        this.lastName = employeeDto.getLastName();
        this.position = employeeDto.getPosition();
        this.birthDate = dateFormat.parse(employeeDto.getBirthDate());
        this.IngressDate = dateFormat.parse(employeeDto.getIngressDate());
        this.dni = employeeDto.getDni();
    }

}
