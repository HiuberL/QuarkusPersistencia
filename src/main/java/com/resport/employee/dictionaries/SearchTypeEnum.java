package com.resport.employee.dictionaries;

import lombok.Getter;

@Getter
public enum SearchTypeEnum {
        NOMBRE("name"),
        APELLIDO("lastname"),
        INGRESO("ingressDate"),
        CEDULA("dni");

        private String name;

        SearchTypeEnum(String name) {
                this.name = name;
        }
}
