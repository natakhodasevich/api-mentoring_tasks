package api.users;

import api.users.commands.models.UnSuccessUserRegisterResponse;
import api.users.commands.models.UserRegisterRequest;
import api.users.commands.service.UserCommandsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UserRegisterFailTest {

    private UserCommandsService userCommandsService;

    @BeforeMethod
    public void setup() {
        userCommandsService = new UserCommandsService();
    }

    @Test
    public void unsuccessfulUserRegister() throws JsonProcessingException {
        UserRegisterRequest userDataRequest =
                new UserRegisterRequest("sydney@fife", "");
        Response response = userCommandsService.registerNewUser(userDataRequest);

        ObjectMapper mapper = new ObjectMapper();
        UnSuccessUserRegisterResponse unSuccessUserRegisterResponse = mapper.readValue(response.getBody().asString(), UnSuccessUserRegisterResponse.class);

        String expectedError = "Missing password";
        Assert.assertNotNull(unSuccessUserRegisterResponse.getError());
        Assert.assertEquals(unSuccessUserRegisterResponse.getError(), expectedError);
    }
}
