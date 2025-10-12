package core.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CommandSenderUtil {

    private CommandSenderUtil() {
    }

    public static <T> Response sendCommand(T command, RequestSpecification requestSpecification, String endpoint) {
        return givenCommand(command)
                .withRequestSpecification(requestSpecification)
                .sendToEndpoint(endpoint);
    }

    public static Response sendCommandWithQueryParams(HashMap<String, String> params, RequestSpecification requestSpecification, String endpoint) {
        return (Response) RestAssured.given(requestSpecification)
                .contentType(ContentType.JSON)
                .queryParams(params)
                .when()
                .post(endpoint).then().log().all().statusCode(200);
    }

    public static <T> CommandSender<T> usingCommandSender() {
        return new CommandSender<T>();
    }

    public static <T> CommandSender<T> givenCommand(T command) {
        return new CommandSender<>(command);
    }

    public static class CommandSender<T> {
        private String endpoint;
        private T command;
        private RequestSpecification requestSpecification;

        public CommandSender() {
        }

        public CommandSender(T command) {
            this.command = command;
        }

        public CommandSender withRequestSpecification(RequestSpecification requestSpecification) {
            this.requestSpecification = requestSpecification;
            return this;
        }

        public CommandSender withCommand(T command) {
            this.command = command;
            return this;
        }

        public CommandSender toEndPoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Response sendToEndpoint(String endpoint) {
            return toEndPoint(endpoint).sendCommand();
        }

        public Response sendCommand() {
            var jsonBody = SerializationUtil.asJsonString(command);

            return given(requestSpecification)
                    .contentType(ContentType.JSON)
                    .body(jsonBody)
                    .when()
                    .post(endpoint);
        }

        public Response sendCommand(T command) {
            this.command = command;
            return sendCommand();
        }
    }
}
