package APItests;

import org.junit.jupiter.api.Test;
import specifications.ApiSpecifications;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//Кейс 4:
//Обновить информацию о пользователе методом patch и сравнить дату обновления с
//текущей датой в системе
public class UpdateUser {
    @Test
    public void updateUser() {
        int userId = 2;
        String updatedName = "John Adams";
        String updatedJob = "Software Engineer";

        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC).withSecond(0).withNano(0);
        String expectedTime = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z"; // ожидаемое время

        String actualUpdatedAt = given()
                .spec(ApiSpecifications.requestSpec())
                .body("{\"name\": \"" + updatedName + "\", \"job\": \"" + updatedJob + "\"}")
                .when()
                .patch("/users/" + userId)
                .then()
                .spec(ApiSpecifications.successResponseSpec())
                .extract()
                .path("updatedAt");

        LocalDateTime actualTime = LocalDateTime.parse(actualUpdatedAt.replace("Z", ""), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .withSecond(0)
                .withNano(0);
        assert expectedTime.equals(actualTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z") : "Время не совпадает!";
    }
    }
