package api.usersApp;

import api.BaseTest;
import api.usersApp.commands.UserCommands;
import api.usersApp.commands.models.UnSuccessUserRegisterResponse;
import api.usersApp.commands.models.UserRegisterRequest;
import api.usersApp.commands.service.UserCommandsService;

import static core.PropertiesLoader.getPropertyByKey;
import static core.utils.UserRandomStringGenerator.*;

import api.usersApp.extendedProperties.service.UserExtendedPropertiesService;
import core.logging.ApiTestContext;
import core.utils.SerializationUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserRegisterTest extends BaseTest {
    private UserCommandsService userCommandsService;
    private UserExtendedPropertiesService userExtendedPropertiesService;
    private UserCommands userCommands;

    @BeforeMethod
    public void setup() {
        userCommandsService = new UserCommandsService();
        userCommands = new UserCommands();
        userExtendedPropertiesService = new UserExtendedPropertiesService();
    }

    @Test(groups = {"smoke"})
    public void registerTest() {
        ApiTestContext.setStepName("step1");
        logger.info("Register user with valid credentials and asserting status code");
        UserRegisterRequest userDataRequest =
                new UserRegisterRequest(getValidUsername(), getValidPassword());

        userCommands.verifyUserIsSuccessfullyRegistered(userDataRequest);
        Response response = userCommandsService.registerUser(userDataRequest);
        Assert.assertEquals(response.statusCode(), 200);

        ApiTestContext.setStepName("step2");
        logger.info("Register user with invalid credentials and asserting status code");
        userDataRequest =
                new UserRegisterRequest(generateRandomAlfanumericOfLength(5), getEmptyString());
        response = userCommandsService.registerUser(userDataRequest);
        Assert.assertEquals(response.statusCode(), 400);
        var unSuccessUserRegisterResponse = SerializationUtil.asObject(response.getBody().asString(), UnSuccessUserRegisterResponse.class);
        String expectedError = getPropertyByKey("MISSING_PASSWORD_ERROR");
        Assert.assertEquals(unSuccessUserRegisterResponse.getError(), expectedError);
    }

    @AfterMethod
    public void tearDown() {
        userExtendedPropertiesService.deleteUser();
    }
}
