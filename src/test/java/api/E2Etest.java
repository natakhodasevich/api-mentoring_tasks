package api;

import api.usersApp.extendedProperties.models.UserTime;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class E2Etest {

    private static final Logger logger = Logger.getLogger("Logger");
    //TODO not ready yet
    @Test
    public void userFlowTest() {

//        String url = "https://reqres.in";
//
//        logger.info("Adding new user");
//       // installSpecification(requestSpecification(url), responseSpecification201());
//        UserTime userTime = new UserTime("morpheus", "leader");
//        UserCreateResponse userCreateResponse = given()
//                .body(userTime)
//                .header("x-api-key", "reqres-free-v1")
//                .when()
//                .post("/api/usersApp")
//                .then().log().all()
//                .extract().as(UserCreateResponse.class);
//        //
//
//        logger.info("Getting new created user");
//        GetUserResponse getUserResponse = (GetUserResponse) given()
//                .header("x-api-key", "reqres-free-v1")
//                .when()
//                .get("/api/usersApp")
//                .then().log().all()
//                .extract().body().jsonPath().getList("data", GetUserResponse.class);
//
//
//
//        logger.info("Updating user");
//
//        logger.info("Getting updated user");
//
//        logger.info("Deleting user");


    }
}
