package api.usersApp;

import api.BaseTest;
import api.usersApp.extendedProperties.service.UserExtendedPropertiesService;
import core.logging.ApiTestContext;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserDeleteTest extends BaseTest {
    private UserExtendedPropertiesService userExtendedPropertiesService;

    @BeforeMethod
    public void setup() {
        userExtendedPropertiesService = new UserExtendedPropertiesService();
    }

    @Test
    public void deleteTest() {
        ApiTestContext.setStepName("step1");
        Response response = userExtendedPropertiesService.deleteUser();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
