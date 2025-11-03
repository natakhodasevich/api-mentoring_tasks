package core.utils;

import core.logging.RequestResponseLoggingFilter;
import core.authentication.AuthorizationFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static core.properties.ApiProperties.*;

public class RequestSpecificationUtil {

    public static RequestSpecification buildReqResApiRequestSpec() {
        String uri = getBaseUriReqRes();
        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .addFilter(new AuthorizationFilter())
                .addFilter(new RequestResponseLoggingFilter())
                .build();
    }

}
