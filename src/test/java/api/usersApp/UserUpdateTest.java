package api.usersApp;

import api.BaseTest;
import api.usersApp.extendedProperties.models.UserTime;
import api.usersApp.extendedProperties.models.UserTimeResponse;
import api.usersApp.extendedProperties.service.UserExtendedPropertiesService;
import core.ApiTestContext;
import core.utils.SerializationUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Clock;

import static core.utils.PropertiesLoader.getPropertyByKey;

public class UserUpdateTest extends BaseTest {

    private static final String USERNAME = getPropertyByKey("USER_UPDATE_USERNAME");
    private static final String PASSWORD = getPropertyByKey("USER_UPDATE_JOB");
    private UserExtendedPropertiesService userExtendedPropertiesService;

    @BeforeMethod
    public void setup() {
        userExtendedPropertiesService = new UserExtendedPropertiesService();
    }

    @Test
    public void updateTest(){
        ApiTestContext.setStepName("step1");
        UserTime userTime = new UserTime(USERNAME, PASSWORD);

        Response response = userExtendedPropertiesService.updateUser(userTime);
        UserTimeResponse userTimeResponse = SerializationUtil.asObject(response.getBody().asString(), UserTimeResponse.class);
        String regex = "^(.{17}).*$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "$1");
        String serverTime = userTimeResponse.getUpdatedAt().replaceAll(regex, "$1");
        Assert.assertEquals(serverTime, currentTime);
    }
}
