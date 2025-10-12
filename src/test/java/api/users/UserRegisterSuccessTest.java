package api.users;

import api.users.commands.models.UserRegisterRequest;
import api.users.commands.models.UserRegisterResponse;
import api.users.commands.service.UserCommandsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserRegisterSuccessTest {

    private UserCommandsService userCommandsService;

    @BeforeMethod
    public void setup() {
        userCommandsService = new UserCommandsService();
    }

    @Test
    public void successRegister() throws JsonProcessingException {
        UserRegisterRequest userDataRequest =
                new UserRegisterRequest("eve.holt@reqres.in", "pistol");

        Response response = userCommandsService.registerNewUser(userDataRequest);

        ObjectMapper mapper = new ObjectMapper();
        UserRegisterResponse userRegisterResponse = mapper.readValue(response.getBody().asString(), UserRegisterResponse.class);

        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";
        Assert.assertNotNull(userRegisterResponse.getId());
        Assert.assertNotNull(userRegisterResponse.getToken());
        Assert.assertEquals(userRegisterResponse.getId(), expectedId);
        Assert.assertEquals(userRegisterResponse.getToken(), expectedToken);
    }
}
