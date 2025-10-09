package core.authentication;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthorizationFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        performRequestWithAuth(requestSpec);
        return ctx.next(requestSpec, responseSpec);
    }

    private void performRequestWithAuth(FilterableRequestSpecification requestSpec) {
        requestSpec.replaceHeader("x-api-key", "reqres-free-v1");
    }
}
