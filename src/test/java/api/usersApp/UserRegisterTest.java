package api.usersApp;

import api.BaseTest;
import api.usersApp.commands.models.UnSuccessUserRegisterResponse;
import api.usersApp.commands.models.UserRegisterRequest;
import api.usersApp.commands.service.UserCommandsService;

import static core.PropertiesLoader.getPropertyByKey;

import core.logging.ApiTestContext;
import core.utils.SerializationUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserRegisterTest extends BaseTest {

    private static final String USERNAME = getPropertyByKey("VALID_USERNAME");
    private static final String PASSWORD = getPropertyByKey("VALID_PASSWORD");
    private static final String INVALID_USERNAME = getPropertyByKey("INVALID_USERNAME");
    private UserCommandsService userCommandsService;

    @BeforeMethod
    public void setup() {
        userCommandsService = new UserCommandsService();
    }

    @Test
    public void registerTest() {

        ApiTestContext.setStepName("step1");
        UserRegisterRequest userDataRequest =
                new UserRegisterRequest(USERNAME, PASSWORD);

        Response response = userCommandsService.registerNewUser(userDataRequest);
        Assert.assertEquals(response.statusCode(), 200);

        ApiTestContext.setStepName("step2");
        userDataRequest =
                new UserRegisterRequest(INVALID_USERNAME, "");
        response = userCommandsService.registerNewUser(userDataRequest);
        Assert.assertEquals(response.statusCode(), 400);
        var unSuccessUserRegisterResponse = SerializationUtil.asObject(response.getBody().asString(), UnSuccessUserRegisterResponse.class);
        String expectedError = getPropertyByKey("MISSING_PASSWORD_ERROR");
        Assert.assertEquals(unSuccessUserRegisterResponse.getError(), expectedError);
    }
}
