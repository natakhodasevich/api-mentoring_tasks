package api.usersApp;

import api.BaseTest;
import api.usersApp.commands.UserCommands;
import api.usersApp.commands.models.UserRegisterRequest;
import api.usersApp.extendedProperties.models.UserUpdateRequest;
import api.usersApp.extendedProperties.models.UserUpdateResponse;
import api.usersApp.extendedProperties.service.UserExtendedPropertiesService;
import core.logging.ApiTestContext;
import core.utils.SerializationUtil;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import static core.utils.UserRandomStringGenerator.getValidPassword;
import static core.utils.UserRandomStringGenerator.getValidUsername;
import static org.testng.Assert.assertTrue;

public class UserUpdateTest extends BaseTest {

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
    public void updateTest() {
        setPreconditions();
        ApiTestContext.setStepName("step1");
        UserUpdateRequest userTime = new UserUpdateRequest(getValidUsername(), getValidPassword());

        logger.info("Updating user and checking the time when was updated");
        Response response = userExtendedPropertiesService.updateUser(userTime);
        UserUpdateResponse userTimeResponse = SerializationUtil.asObject(response.getBody().asString(), UserUpdateResponse.class);
        Instant currentTime = Clock.systemUTC().instant();
        Instant serverTime = Instant.parse(userTimeResponse.getUpdatedAt());
        long diffSeconds = Duration.between(serverTime, currentTime).abs().toSeconds();
        assertTrue(diffSeconds < 5, "Difference is " + diffSeconds + " seconds");
    }

    @AfterMethod
    public void tearDown() {
        userExtendedPropertiesService.deleteUser();
    }
}
