package api;

import api.colors.entityReads.models.ColorDataResponse;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class ReqResTest {

    private final static String url = "https://reqres.in";

    @Test
    public void checkColorValue() {
        Specification.installSpecification(Specification.requestSpecification(url), Specification.responseSpecification200());
        List<ColorDataResponse> colorsList = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorDataResponse.class);

        colorsList.forEach(colorData -> {
                    String color = colorData.getColor();
                    assertNotNull(color, "Color value should not be null");
                    assertFalse(color.isEmpty(), "Color value should not be empty");
                    assertTrue(color.startsWith("#"), "Color should start with #");
                }
        );
    }

    @Test
    public void checkPantoneValuePattern() {
        List<ColorDataResponse> colorsList = given()
                .when()
                .contentType(ContentType.JSON).get(url + "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorDataResponse.class);

        colorsList.forEach(colorData -> {
                    String pantoneValue = colorData.getPantone_value();
                    assertNotNull(pantoneValue, "Pantone value should not be null");
                    assertFalse(pantoneValue.isEmpty(), "Pantone value should not be empty");
                    assertTrue(pantoneValue.matches("\\d{2}-\\d{4}"), "Pantone should match the pattern 12-3456");
                }
        );
    }

    @Test
    public void successRegister() {
        Specification.installSpecification(Specification.requestSpecification(url),
                Specification.responseSpecification200());

        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        UserRegisterRequest userDataRequest =
                new UserRegisterRequest("eve.holt@reqres.in", "pistol");

        UserRegisterResponse userRegisterResponse = given()
                .header("x-api-key", "reqres-free-v1")
                .body(userDataRequest)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(UserRegisterResponse.class);
        Assert.assertNotNull(userRegisterResponse.getId());
        Assert.assertNotNull(userRegisterResponse.getToken());
        Assert.assertEquals(userRegisterResponse.getId(), id);
        Assert.assertEquals(userRegisterResponse.getToken(), token);
    }

    @Test
    public void unsuccessfulUserRegister() {
        Specification.installSpecification(Specification.requestSpecification(url),
                Specification.responseSpecification400());

        String expectedError = "Missing password";

        UserRegisterRequest userDataRequest =
                new UserRegisterRequest("sydney@fife", "");

        UnSuccessUserRegisterResponse unSuccessUserRegisterResponse = given()
                .header("x-api-key", "reqres-free-v1")
                .body(userDataRequest)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(UnSuccessUserRegisterResponse.class);
        Assert.assertNotNull(unSuccessUserRegisterResponse.getError());
        Assert.assertEquals(unSuccessUserRegisterResponse.getError(), expectedError);
    }



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
