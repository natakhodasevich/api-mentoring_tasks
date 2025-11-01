package api.usersApp.commands.service;

import api.usersApp.commands.UserCommandsEndpoints;
import api.usersApp.commands.models.UserCreateRequest;
import api.usersApp.commands.models.UserRegisterRequest;
import io.restassured.response.Response;

import static core.utils.CommandSenderUtil.sendCommand;
import static core.utils.RequestSpecificationUtils.buildReqResApiRequestSpec;

public class UserCommandsService {

    public Response registerNewUser(UserRegisterRequest command) {
        return sendCommand(command, buildReqResApiRequestSpec(), UserCommandsEndpoints.REGISTER_USER);
    }

    public Response createNewUser(UserCreateRequest command) {
        return sendCommand(command, buildReqResApiRequestSpec(), UserCommandsEndpoints.CREATE_USER);
    }
}
