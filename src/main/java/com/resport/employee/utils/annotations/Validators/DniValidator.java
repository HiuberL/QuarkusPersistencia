package com.resport.employee.utils.annotations.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.resport.employee.utils.annotations.DNI;

public class DniValidator implements ConstraintValidator<DNI, String> {
    @Override
    public boolean isValid(String dni, ConstraintValidatorContext constraintValidatorContext) {
        if (!dni.matches("^[0-9]+$"))
            return false;

        int sumar = 0;
        int i = 0;
        int resultado = 0;
        int mayor = 0;
        for (int e : dni.toCharArray()) {
            i += 1;
            if (i < 10) {
                if (i % 2 == 0) {

                    resultado = Integer.parseInt(String.valueOf((char) e)) * 1;
                } else {
                    resultado = Integer.parseInt(String.valueOf((char) e)) * 2;
                }
                if (resultado >= 10) {
                    resultado -= 9;
                }
                sumar += resultado;
            }
        }
        for (int s = 10; sumar + 10 > s; s += 10) {
            mayor = s;
        }
        int ultimo = Integer.parseInt(dni.substring(9));
        if ((mayor - sumar) > 10) {
            sumar = mayor - sumar - 10;
        } else {
            sumar = mayor - sumar;
        }
        if ((sumar) == ultimo)
            return true;
        else
            return false;
    }
}
