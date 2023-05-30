package com.resport.employee.models.dto;

import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.resport.employee.utils.annotations.DNI;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {
    @Schema(example = "John Doe")
    @Size(max = 50, min = 3, message = "El nombre puede ser de máximo 50 caracteres")
    private String name;
    @Schema(example = "Maruly Chow")
    @Size(max = 60, min = 3, message = "El apellido puede ser de máximo 50 caracteres")
    private String lastName;
    @Schema(example = "Empleado")
    @Size(max = 15, min = 3, message = "La posición debe ser descriptiva y máximo 15 caracteres")
    private String position;
    @Schema(example = "01/01/2022")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private String birthDate;
    @Schema(example = "01/01/2023")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private String ingressDate;
    @DNI
    @Schema(example = "0999999999")
    private String dni;

}
