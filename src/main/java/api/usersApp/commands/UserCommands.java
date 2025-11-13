package api.usersApp.commands;

import api.usersApp.commands.models.UserRegisterRequest;
import api.usersApp.commands.service.UserCommandsService;
import io.restassured.response.Response;
import org.testng.Assert;

public class UserCommands {
    private final UserCommandsService userCommandsService = new UserCommandsService();

    public void verifyUserIsSuccessfullyRegistered(UserRegisterRequest userRegisterRequest) {
        Response response = userCommandsService.registerUser(userRegisterRequest);
        Assert.assertEquals(response.statusCode(), 200);
    }
}
