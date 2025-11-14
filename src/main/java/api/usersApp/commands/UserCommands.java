package api.usersApp.commands;

import api.usersApp.commands.models.UserRegisterRequest;
import api.usersApp.commands.service.UserCommandsService;
import io.restassured.response.Response;
import org.testng.Assert;

public class UserCommands {
    private final UserCommandsService userCommandsService = new UserCommandsService();

    /**
     * Creates a new user via API and checks status code 200.
     *
     * @param userRegisterRequest the user creation details
     */
    public void verifyUserIsSuccessfullyRegistered(UserRegisterRequest userRegisterRequest) {
        Response response = userCommandsService.registerUser(userRegisterRequest);
        Assert.assertEquals(response.statusCode(), 200);
    }
}
