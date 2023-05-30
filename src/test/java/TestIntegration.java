import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.resport.employee.models.dto.EmployeeDto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TestIntegration {

    @Test
    @Order(1)
    @DisplayName("Test de la solicitud POST /empleados")
    public void testCreate() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Kevin Lizano");
        employeeDto.setLastName("Lizano Flores");
        employeeDto.setDni("0931850358");
        employeeDto.setBirthDate("01/01/2022");
        employeeDto.setIngressDate("01/01/2023");
        employeeDto.setPosition("empleado");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(employeeDto)
                .when().post("/empleados")
                .then().statusCode(200)
                .extract().response();

        assertTrue(response.jsonPath().getLong("codeError") == 0, "El resultado debería ser cero");
    }

    @Test
    @Order(2)
    @DisplayName("Test de la solicitud GET /empleados")
    public void testGetAll() {
        Response response = RestAssured.given()
                .when().get("/empleados")
                .then().statusCode(200)
                .extract().response();

        assertTrue(response.jsonPath().getLong("codeError") == 0, "El resultado debería ser cero");
        assertFalse(response.jsonPath().getList("data").isEmpty(), "La propiedad no debe ser nula");
    }

    @Test
    @Order(3)
    @DisplayName("Test de la solicitud PUT /empleados")
    public void testUpdate() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Kevin Lizano");
        employeeDto.setLastName("Lizano Mariano");
        employeeDto.setDni("0931850358");
        employeeDto.setBirthDate("01/01/2022");
        employeeDto.setIngressDate("01/01/2023");
        employeeDto.setPosition("empleado");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(employeeDto)
                .pathParam("dni", "0931850358")
                .when().put("/empleados/{dni}")
                .then().statusCode(200)
                .extract().response();

        assertTrue(response.jsonPath().getLong("codeError") == 0, "El resultado debería ser cero");
    }

    @Test
    public void testDelete() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("dni", "0931850351")
                .when().delete("/empleados/{dni}")
                .then().statusCode(404)
                .extract().response();

        assertFalse(response.jsonPath().getLong("codeError") == 0, "El resultado no debería ser cero");
    }

    @Test
    @DisplayName("Test de la solicitud POST /empleados")
    public void testCreateParalel() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Kevin Lizano");
        employeeDto.setLastName("Lizano Flores");
        employeeDto.setDni("0931850359");
        employeeDto.setBirthDate("01/01/2022");
        employeeDto.setIngressDate("01/01/2023");
        employeeDto.setPosition("empleado");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(employeeDto)
                .when().post("/empleados")
                .then().statusCode(400)
                .extract().response();

        assertTrue(response.jsonPath().getLong("codeError") != 0, "El resultado no debería ser cero");
    }

}