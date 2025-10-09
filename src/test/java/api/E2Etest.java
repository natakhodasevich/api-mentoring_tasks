package api;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static api.Specification.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class E2Etest {

    private static final Logger logger = Logger.getLogger("Logger");

    @Test
    public void userFlowTest() {

        String url = "https://reqres.in";

        logger.info("Adding new user");
        installSpecification(requestSpecification(url), responseSpecification201());
        UserTime userTime = new UserTime("morpheus", "leader");
        UserCreateResponse userCreateResponse = given()
                .body(userTime)
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post("/api/users")
                .then().log().all()
                .extract().as(UserCreateResponse.class);
        //

        logger.info("Getting new created user");
        GetUserResponse getUserResponse = (GetUserResponse) given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", GetUserResponse.class);



        logger.info("Updating user");

        logger.info("Getting updated user");

        logger.info("Deleting user");


    }
}
