package api;
import api.usersApp.extendedProperties.models.UserTime;
import api.usersApp.extendedProperties.models.UserTimeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;

public class ReqResTest {
    //TODO not ready yet
    private final static String url = "https://reqres.in";

    @Test
    public void deleteTest() {

        Specification.installSpecification(Specification.requestSpecification(url), Specification.responseSpecification204());

        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/api/usersApp/2")
                .then().log().all();
    }


}
