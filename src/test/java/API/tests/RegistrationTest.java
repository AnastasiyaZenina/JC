package API.tests;

import API.specifications.ApiSpecifications;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

//Кейс 1:
//Протестировать регистрацию пользователя в системе, необходимо создание 2 тестов:
//успешная регистрация с валидными данными
//регистрация с ошибкой из-за отсутствия пароля и проверить,что статус-код
//в ответе 400
public class RegistrationTest {
    @Test
    public void successfulRegistration() {
        given()
                .spec(ApiSpecifications.requestSpec())
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
                .when()
                .post("/register")
                .then()
                .spec(ApiSpecifications.successResponseSpec())
                .body("id", notNullValue())
                .body("token", notNullValue());
    }

    @Test
    public void unsuccessfulRegistrationWithoutPassword() {
        given()
                .spec(ApiSpecifications.requestSpec())
                .body("{\"email\": \"sydney@fife\"}")
                .when()
                .post("/register")
                .then()
                .spec(ApiSpecifications.errorRequestResponseSpec());
    }
}

