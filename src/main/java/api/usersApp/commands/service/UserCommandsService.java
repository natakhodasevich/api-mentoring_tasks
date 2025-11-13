package api.usersApp.commands.service;

import api.usersApp.commands.UserCommandsEndpoints;
import api.usersApp.commands.models.UserCreateRequest;
import api.usersApp.commands.models.UserRegisterRequest;
import io.restassured.response.Response;

import static core.utils.CommandsUtil.sendCommand;
import static core.utils.RequestSpecificationUtil.buildReqResApiRequestSpec;

public class UserCommandsService {

    /**
     * Registers a user via the API.
     *
     * @param command the registration details
     * @return the API response
     */
    public Response registerUser(UserRegisterRequest command) {
        return sendCommand(command, buildReqResApiRequestSpec(), UserCommandsEndpoints.REGISTER_USER);
    }

    /**
     * Creates a new user via the API.
     *
     * @param command the user creation details
     * @return the API response
     */
    public Response createNewUser(UserCreateRequest command) {
        return sendCommand(command, buildReqResApiRequestSpec(), UserCommandsEndpoints.CREATE_USER);
    }
}
