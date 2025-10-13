package core.utils;

import core.RetryCaller;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PayloadEntityReadsUtil {


    public static <T> PayloadEntityReader<T> givenEntityType(Class<T> entityType) {
        return new PayloadEntityReader<T>().withEntityType(entityType);
    }

    public static <T> T getEntity(RequestSpecification requestSpecification, String entityId, String baseUrl, Class<T> classType) {
        return givenEntityType(classType)
                .withRequestSpecification(requestSpecification)
                .withEntityId(entityId)
                .getEntityFromUrl(baseUrl);
    }


    public static class PayloadEntityReader<T> {
        private final List<String> entityIds;
        private Class<T> entityType;
        private Class<T[]> arrayEntityType;
        private RequestSpecification requestSpecification;
        private String url;

        public PayloadEntityReader() {
            entityIds = new ArrayList<>();
        }

        public PayloadEntityReader<T> withEntityType(Class<T> entityType) {
            this.entityType = entityType;
            return this;
        }

        public PayloadEntityReader<T> withArrayEntityType(Class<T[]> arrayEntityType) {
            this.arrayEntityType = arrayEntityType;
            return this;
        }

        public PayloadEntityReader<T> withRequestSpecification(RequestSpecification requestSpecification) {
            this.requestSpecification = requestSpecification;
            return this;
        }

        public PayloadEntityReader<T> withEntityId(String entityId) {
            this.entityIds.add(entityId);
            return this;
        }

        public PayloadEntityReader<T> fromUrl(String url) {
            this.url = url;
            return this;
        }

        public static <T> PayloadEntityReader<T> givenEntityArrayType(Class<T[]> entityArrayType) {
            return new PayloadEntityReader<T>().withArrayEntityType(entityArrayType);
        }

        public T getEntityFromUrl(String url) {
            return fromUrl(url).getEntity();
        }

        public T getEntity() {
            if (!entityIds.isEmpty()) {
                this.url = UrlUtil.combineUrl(this.url, entityIds.toArray());
            }

            var jsonResponse = getResponse()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body().asString();


            return SerializationUtil.asObject(jsonResponse, entityType);
        }

        public List<T> getEntitiesFromUrl(String url, boolean isJsonPathNeeded, Class<T> clazz, String path) {
            return isJsonPathNeeded ? fromUrl(url).getEntitiesListFromJsonpath(path, clazz) : fromUrl(url).getEntitiesList(clazz);
        }

        public List<T> getEntitiesListFromJsonpath(String path, Class<T> clazz) {

            List<T> jsonResponse = getResponse()
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .body().jsonPath().getList(path, clazz);

            return jsonResponse;
        }


        public <T> List<T> getEntitiesList(Class<T> clazz) {
            List<T> jsonResponse = getResponse()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(new TypeRef<List<T>>() {});
            return jsonResponse;
        }


        public List<T> getExtendedPropertiesFromUrl(String url) {
            return fromUrl(url).getExtendedProperties();
        }

        public List<T> getExtendedProperties() {
            var jsonResponse = getResponse()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();
            return Arrays.asList(SerializationUtil.asObject(jsonResponse, arrayEntityType));
        }

        public int getResponseStatusCode(String url) {
            return fromUrl(url).getExtendedPropertiesStatusCode();
        }

        public int getExtendedPropertiesStatusCode() {
            return getResponse()
                    .then()
                    .extract()
                    .response()
                    .getStatusCode();
        }

        private Response getResponse() {
            try {
                return RetryCaller.<Response, Exception>retry(Exception.class, 1)
                        .call(() -> {
                            try {
                                return given(requestSpecification)
                                        .contentType(ContentType.JSON)
                                        .when()
                                        .get(url);
                            } catch (Exception e) {
                                throw new Exception("Could not get response from %s. Retrying...".formatted(url));
                            }

                        });
            } catch (Exception e) {
                java.util.logging.Logger.getLogger("").info("Could not get response");
            }
            return null;
        }
    }
}
