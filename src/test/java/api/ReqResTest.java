package api;
import api.users.commands.models.UserRegisterRequest;
import api.users.commands.models.UserRegisterResponse;
import api.users.extendedProperties.models.UserTime;
import api.users.extendedProperties.models.UserTimeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;

public class ReqResTest {

    private final static String url = "https://reqres.in";

    @Test
    public void deleteTest() {

        Specification.installSpecification(Specification.requestSpecification(url), Specification.responseSpecification204());

        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/api/users/2")
                .then().log().all();
    }

    @Test
    public void updateTest() {

        Specification.installSpecification(Specification.requestSpecification(url), Specification.responseSpecification200());

        UserTime userTime = new UserTime("morpheus", "zion resident");

        UserTimeResponse userTimeResponse = given()
                .header("x-api-key", "reqres-free-v1")
                .body(userTime)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);

        String regex = "^(.{17}).*$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll( regex , "$1");
        String serverTime = userTimeResponse.getUpdatedAt().replaceAll(regex, "$1");
        Assert.assertEquals(serverTime, currentTime);
    }
}
