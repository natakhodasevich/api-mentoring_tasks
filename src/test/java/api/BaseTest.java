package api;

import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    protected String testName;
    protected String testId;

    @BeforeMethod
    public void setupTestContext(ITestResult result) {
        testName = result.getMethod().getMethodName();
        testId = ZonedDateTime.now(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }
}

