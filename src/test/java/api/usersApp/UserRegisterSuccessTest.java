package api.usersApp;

import api.usersApp.commands.models.UnSuccessUserRegisterResponse;
import api.usersApp.commands.models.UserRegisterRequest;
import api.usersApp.commands.service.UserCommandsService;
import com.fasterxml.jackson.core.JsonProcessingException;

import static core.utils.PropertiesLoader.getPropertyByKey;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserRegisterSuccessTest {

    private static final String USERNAME = getPropertyByKey("VALID_USERNAME");
    private static final String PASSWORD = getPropertyByKey("VALID_PASSWORD");
    private static final String INVALID_USERNAME = getPropertyByKey("INVALID_USERNAME");
    private static final Logger logger = LoggerFactory.getLogger(UserRegisterSuccessTest.class);
    private UserCommandsService userCommandsService;

    @BeforeMethod
    public void setup() {
        userCommandsService = new UserCommandsService();
    }

    @Test
    public void successRegister() throws JsonProcessingException {
        logger.info("Step 1 - Successful register");
        UserRegisterRequest userDataRequest =
                new UserRegisterRequest(USERNAME, PASSWORD);

        Response response = userCommandsService.registerNewUser(userDataRequest);
        Assert.assertEquals(response.statusCode(), 200);

        logger.info("Step 2 - UnSuccessful register");
        userDataRequest =
                new UserRegisterRequest(INVALID_USERNAME, "");
        response = userCommandsService.registerNewUser(userDataRequest);
        Assert.assertEquals(response.statusCode(), 400);

        ObjectMapper mapper = new ObjectMapper();
        UnSuccessUserRegisterResponse unSuccessUserRegisterResponse = mapper.readValue(response.getBody().asString(), UnSuccessUserRegisterResponse.class);
        String expectedError = getPropertyByKey("MISSING_PASSWORD_ERROR");
        Assert.assertEquals(unSuccessUserRegisterResponse.getError(), expectedError);
    }
}
