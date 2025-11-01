package api;

import api.usersApp.extendedProperties.models.UserUpdateRequest;
import api.usersApp.extendedProperties.models.UserUpdateResponse;
import api.usersApp.extendedProperties.service.UserExtendedPropertiesService;
import core.logging.ApiTestContext;
import core.utils.SerializationUtil;
import io.restassured.response.Response;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;

import static core.utils.PropertiesLoader.getPropertyByKey;

public class UserFlowTest extends BaseTest {
//TODO wip part
    private static final String USERNAME = getPropertyByKey("VALID_USERNAME");
    private static final String PASSWORD = getPropertyByKey("VALID_PASSWORD");
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserFlowTest.class);
    private UserExtendedPropertiesService userExtendedPropertiesService;

    @BeforeMethod
    public void setup() {
        userExtendedPropertiesService = new UserExtendedPropertiesService();
    }

    @Test
    public void userFlowTest() {

        ApiTestContext.setStepName("Step1");
        logger.info("Adding new user");


        ApiTestContext.setStepName("Step2");
        logger.info("Getting new user");


        ApiTestContext.setStepName("Step3");
        logger.info("Updating user");
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(USERNAME, PASSWORD);
        Response userUpdateResponse = userExtendedPropertiesService.updateUser(userUpdateRequest);
        UserUpdateResponse userTimeResponse = SerializationUtil.asObject(userUpdateResponse.getBody().asString(), UserUpdateResponse.class);
        String regex = "^(.{17}).*$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "$1");
        String serverTime = userTimeResponse.getUpdatedAt().replaceAll(regex, "$1");
        Assert.assertEquals(serverTime, currentTime);

        ApiTestContext.setStepName("Step4");
        logger.info("Getting user after update");

        ApiTestContext.setStepName("Step5");
        logger.info("Deleting user");
        Response deleteUserResponse = userExtendedPropertiesService.deleteUser();
        Assert.assertEquals(deleteUserResponse.statusCode(), 204);
    }
}
