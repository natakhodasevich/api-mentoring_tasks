package core.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ExtendedPropertiesUtils {
    public static Response sendDeleteRequest(RequestSpecification requestSpecification, String endpoint) {
        return given(requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint);
    }

    public static Response sendPutRequest(Object command, RequestSpecification requestSpecification, String endpoint) {
        var jsonBody = SerializationUtil.asJsonString(command);
        return RestAssured.given(requestSpecification)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put(endpoint);
    }
}
