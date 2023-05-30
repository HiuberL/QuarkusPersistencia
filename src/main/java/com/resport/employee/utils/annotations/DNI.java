package com.resport.employee.utils.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.resport.employee.utils.annotations.Validators.DniValidator;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DniValidator.class)
public @interface DNI {
    public String message() default "El número de cédula es incorrecta";

    public abstract java.lang.Class<?>[] groups() default {};

    public abstract java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
}
