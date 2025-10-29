package api;

import core.logging.ApiTestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    @BeforeMethod
    public void setupTestContext(ITestResult result) {
        ApiTestContext.setTestName(result.getMethod().getMethodName());
        ApiTestContext.setTestId(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now()));
    }
}

