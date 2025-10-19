package core;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.IOException;

public class RequestResponseLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);


        String testName = ApiTestContext.getTestName();
        String testId = ApiTestContext.getTestId();
        String stepName = ApiTestContext.getStepName();


        StringBuilder requestLog = new StringBuilder();
        requestLog.append("=== REQUEST ===\n");
        requestLog.append("Method: ").append(requestSpec.getMethod()).append("\n");
        requestLog.append("URI: ").append(requestSpec.getURI()).append("\n");
        requestLog.append("Headers: ").append(requestSpec.getHeaders()).append("\n");
        requestLog.append("Body: ").append((String) requestSpec.getBody()).append("\n");

        StringBuilder responseLog = new StringBuilder();
        responseLog.append("=== RESPONSE ===\n");
        responseLog.append("Status Code: ").append(response.getStatusCode()).append("\n");
        responseLog.append("Headers: ").append(response.getHeaders()).append("\n");
        responseLog.append("Body: ").append(response.getBody().asString()).append("\n");

        try {

            ReportSaver.saveLogs(testName, testId, stepName + "_request", requestLog.toString());
            ReportSaver.saveLogs(testName, testId, stepName + "_response", responseLog.toString());

        } catch (IOException e) {
            e.printStackTrace(); // Или логгер
        }

        return response;
    }
}
