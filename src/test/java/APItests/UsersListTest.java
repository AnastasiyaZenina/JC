package APItests;

import org.junit.jupiter.api.Test;
import specifications.ApiSpecifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//Кейс 2:
//1.Получить список пользователей страницы
//2.Убедиться, что email пользователей имеет окончание @reqres.in
public class UsersListTest {
    @Test
    public void getUsersList() {
        given()
                .spec(ApiSpecifications.requestSpec())
                .when()
                .get("/users?page=2")
                .then()
                .spec(ApiSpecifications.userListResponseSpec());
    }

    @Test
    public void checkUsersEmail() {
        given()
                .spec(ApiSpecifications.requestSpec())
                .when()
                .get("/users?page=2")
                .then()
                .spec(ApiSpecifications.userListResponseSpec())
                .body("data.every { it.email.endsWith('@reqres.in') }", equalTo(true));
    }
}
