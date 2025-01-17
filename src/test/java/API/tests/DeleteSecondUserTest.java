package API.tests;

import API.specifications.ApiSpecifications;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

//Кейс 3:
//Удалить второго пользователя и проверить что статус-код 204
public class DeleteSecondUserTest {
    @Test
    public void deleteSecondUser () {
        int userId = 2;
        given()
                .spec(ApiSpecifications.requestSpec())
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);
    }
}
