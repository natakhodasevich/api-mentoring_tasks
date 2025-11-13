package api;

import api.usersApp.commands.models.UserCreateRequest;
import api.usersApp.commands.service.UserCommandsService;
import api.usersApp.entityReads.UserEntityReads;
import api.usersApp.entityReads.models.GetUserResponse;
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

import static core.PropertiesLoader.getPropertyByKey;

public class UserFlowTest extends BaseTest {
    private static final String USERNAME = getPropertyByKey("VALID_USERNAME");
    private static final String PASSWORD = getPropertyByKey("VALID_PASSWORD");
    private static final String USER_NAME = "morpheus";
    private static final String USER_JOB = "leader";
    private static final String USER_LAST_NAME_AFTER_UPDATE = "zion resident";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserFlowTest.class);
    private UserExtendedPropertiesService userExtendedPropertiesService;
    private UserCommandsService userCommandsService;
    private UserEntityReads userEntityReads;

    @BeforeMethod
    public void setup() {
        userExtendedPropertiesService = new UserExtendedPropertiesService();
        userEntityReads = new UserEntityReads();
        userCommandsService = new UserCommandsService();
    }

    @Test(groups = {"smoke"})
    public void userFlowTest() {

        ApiTestContext.setStepName("Step1");
        logger.info("Adding new user");
        UserCreateRequest userCreateRequest = new UserCreateRequest(USER_NAME, USER_JOB);
        Response userCreateResponse = userCommandsService.createNewUser(userCreateRequest);
        Assert.assertEquals(userCreateResponse.statusCode(), 201);

        ApiTestContext.setStepName("Step2");
        logger.info("Getting new user");
        GetUserResponse getUserResponse = userEntityReads.getUserByName(USER_NAME);

        Assert.assertNotNull(getUserResponse, "The user for the provided name was not added");

        Assert.assertNotNull(getUserResponse.getId(), "User ID is null");
        Assert.assertNotNull(getUserResponse.getEmail(), "User email is null");
        Assert.assertNotNull(getUserResponse.getFirst_name(), "User first name is null");
        Assert.assertNotNull(getUserResponse.getLast_name(), "User last name is null");
        Assert.assertNotNull(getUserResponse.getAvatar(), "User avatar is null");

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
        getUserResponse = userEntityReads.getUserByName(USER_NAME);
        Assert.assertEquals(getUserResponse.getLast_name(), USER_LAST_NAME_AFTER_UPDATE);

        ApiTestContext.setStepName("Step5");
        logger.info("Deleting user");
        Response deleteUserResponse = userExtendedPropertiesService.deleteUser();
        Assert.assertEquals(deleteUserResponse.statusCode(), 204);
        Assert.assertNull(userEntityReads.getUserByName(USER_NAME));
    }
}
