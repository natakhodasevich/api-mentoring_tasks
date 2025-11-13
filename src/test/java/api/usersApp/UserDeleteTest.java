package api.usersApp;

import api.BaseTest;
import api.usersApp.commands.UserCommands;
import api.usersApp.commands.models.UserRegisterRequest;
import api.usersApp.extendedProperties.service.UserExtendedPropertiesService;
import core.logging.ApiTestContext;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static core.utils.UserRandomStringGenerator.getValidPassword;
import static core.utils.UserRandomStringGenerator.getValidUsername;

public class UserDeleteTest extends BaseTest {
    private UserExtendedPropertiesService userExtendedPropertiesService;
    private UserCommands userCommands;

    @BeforeMethod
    public void setup() {
        userExtendedPropertiesService = new UserExtendedPropertiesService();
        userCommands = new UserCommands();
    }

    private void setPreconditions() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(getValidUsername(), getValidPassword());
        userCommands.verifyUserIsSuccessfullyRegistered(userRegisterRequest);
    }

    @Test(groups = {"regression"})
    public void deleteTest() {
        setPreconditions();
        ApiTestContext.setStepName("step1");
        logger.info("Deleting user and asserting status code");
        Response response = userExtendedPropertiesService.deleteUser();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
